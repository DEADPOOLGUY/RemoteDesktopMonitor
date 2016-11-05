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
	static int FILEPORT = 8888;//文件发送功能端口8888
	static int MESSAGEPORT = 8889;//消息发送功能端口8889
	HashMap<String,InetAddress> map = new HashMap<String,InetAddress>();//存储已连接到的客户端ip数据
    IndexPage indexPage;
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
	public Server(){
		indexPage = new IndexPage(this);//实例化主页类
	}
	
	public static void main(String[] args) throws IOException  {
		 
	     Server server = new Server();
	     //server.recImg();
	     server.listenerForClients();//启动客户端IP地址监听
	     server.receiveMessage();//启动消息接收监听
	     server.receiveFile();//启动文件接收监听

	     
	}
	//服务器消息接收监听功能
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
	//服务器文件接收监听功能
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
	//服务器客户端IP地址监听功能
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
	//服务器屏幕监控功能
	public void recImg() throws IOException {
		
		
		final ReceiveImg ri = new ReceiveImg(indexPage.jbtArray);//实例化屏幕监控功能类
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
		System.out.println("屏幕监控服务已关闭");
	}
	//服务器远程关机控制功能
	public void sendShutDown() {		
		    new SendMessage("shutdown",map,MESSAGEPORT);
	}	
	//服务器远程睡眠控制功能
	public void sendSleep() {		
	    new SendMessage("sleep",map,MESSAGEPORT);
    }
	
	public void sendSpecifiedMessage(String string) {
		HashMap<String,InetAddress> theMap = selectUsers(indexPage.getValues(), map);
	    if (theMap.isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "未选择客户端或所选择客户端不在线", "提示", JOptionPane.DEFAULT_OPTION);
		} else {
			new SendMessage(string,theMap,8879);
		}

}
	//服务器发送消息功能
	public void sendMessage(String string) {
			//HashMap<String,InetAddress> theMap = selectUsers(indexPage.getValues(), map);
		    new SendMessage(string,map,8879);	
	}
	//服务器发送文件功能
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
						System.out.println("有");
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
