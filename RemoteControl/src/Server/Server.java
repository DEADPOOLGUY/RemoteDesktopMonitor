package Server;

import java.awt.Image;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerError;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.omg.CORBA.PUBLIC_MEMBER;

import GUI.IndexPage;


public class Server{
	static int SCREENPORT = 8887;
	static int FILEPORT = 8888;//�ļ����͹��ܶ˿�8888
	static int MESSAGEPORT = 8889;//��Ϣ���͹��ܶ˿�8889
	HashMap<String,InetAddress> map = new HashMap<String,InetAddress>();//�洢�����ӵ��Ŀͻ���ip����
    IndexPage indexPage;
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
	public Server(){
		indexPage = new IndexPage(this);//ʵ������ҳ��
	}
	
	public static void main(String[] args) throws IOException  {
		 
	     Server server = new Server();
	     //server.recImg();
	     server.listenerForClients();//�����ͻ���IP��ַ����
	     server.receiveMessage();//������Ϣ���ռ���
	     server.receiveFile();//�����ļ����ռ���

	     
	}
	//��������Ϣ���ռ�������
	public void receiveMessage() throws IOException{
		
		final ReceiveMessage rm = new ReceiveMessage(indexPage.jta1);
		Thread th1 = new Thread(new Runnable() {
				public void run() {
					rm.receive();
				} 
	    });
		th1.start();
		//rf.receive();
		
	}
	//�������ļ����ռ�������
	public void receiveFile() throws IOException{
		
		final ReceiveFile rf = new ReceiveFile(indexPage.jta1);
		Thread th1 = new Thread(new Runnable() {
				public void run() {
					rf.receive();
				} 
	    });
		th1.start();
		//rf.receive();
		
	}
	//�������ͻ���IP��ַ��������
	public void listenerForClients() throws IOException{
		
		final ListenerForClients lfc = new ListenerForClients(indexPage.jta1,map);
		Thread th2 = new Thread(new Runnable() {
			public void run() {
				lfc.listen();
			} 
        });
	    th2.start();
		//lfc.listen();
		
	} 
	//��������Ļ��ع���
	public void recImg() throws IOException {
		
		
		final ReceiveImg ri = new ReceiveImg(indexPage.jbtArray);//ʵ������Ļ��ع�����
		Thread  thRec = new Thread(new Runnable() {
				public void run() {
					ri.receive();
				} 
	    });
		fixedThreadPool.execute(thRec);
		//thRec.start();
	
		
	}
		
	public void stopRecImg(){ 
		fixedThreadPool.shutdown();
		fixedThreadPool.shutdownNow();
		System.out.println("��Ļ��ط����ѹر�");
	}
	//������Զ�̹ػ����ƹ���
	public void sendShutDown() {		
		    new SendMessage("shutdown",map,MESSAGEPORT);
	}	
	//������Զ��˯�߿��ƹ���
	public void sendSleep() {		
	    new SendMessage("sleep",map,MESSAGEPORT);
    }
	
	public void sendSpecifiedMessage(String string) {
		HashMap<String,InetAddress> theMap = selectUsers(indexPage.getValues(), map);
	    if (theMap.isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "δѡ��ͻ��˻���ѡ��ͻ��˲�����", "��ʾ", JOptionPane.DEFAULT_OPTION);
		} else {
			new SendMessage(string,theMap,8879);
		}

}
	//������������Ϣ����
	public void sendMessage(String string) {
			//HashMap<String,InetAddress> theMap = selectUsers(indexPage.getValues(), map);
		    new SendMessage(string,map,8879);	
	}
	//�����������ļ�����
	public void sendFlie(){
		 	new SendFile(FILEPORT,map);
	}
	
	public HashMap<String,InetAddress> selectUsers(int[] select,HashMap<String,InetAddress> orginMap){
		int i;
		System.out.println("ok");
		HashMap<String,InetAddress> newMap = new HashMap<String,InetAddress>();
		if (select.length == 0) {
			return newMap;
		} else {
			for(i=0;i<select.length;i++){
				System.out.println("**"+select[i]);
				for(String key:orginMap.keySet()){
					System.out.println("\\"+key);
					if(String.valueOf(select[i]+1).equals(key)){
						System.out.println("��");
						newMap.put(key,orginMap.get(key));
						break;
					}				
				}
			}
			System.out.println(newMap.isEmpty());
			for(String key:newMap.keySet()){
				System.out.println(newMap.get(key).getHostAddress());
			}
			return newMap;
		}

	}

}
