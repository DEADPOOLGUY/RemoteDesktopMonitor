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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTextArea;

import org.omg.CORBA.PUBLIC_MEMBER;

import GUI.IndexPage;


public class Server{
	static int SCREENPORT = 8887;
	static int FILEPORT = 8888;
	static int MESSAGEPORT = 8889;
	HashMap<String,InetAddress> map = new HashMap<String,InetAddress>();
	//LinkedList<InetAddress> map = new LinkedList<InetAddress>();
    IndexPage indexPage;
	
	public Server(){
		indexPage = new IndexPage(this);
		System.out.println(indexPage.filePath);
	}
	
	public static void main(String[] args) throws IOException  {
		 
	     Server server = new Server();
	     //server.recImg();
	     server.listenerForClients();
	     server.receiveFile();
	     server.receiveMessage();
	     
	}
	
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
	
	public void receiveFile() throws IOException{
		
		final ReceiveFile rf = new ReceiveFile(indexPage.jta1,indexPage.filePath);
		Thread th1 = new Thread(new Runnable() {
				public void run() {
					rf.receive();
				} 
	    });
		th1.start();
		//rf.receive();
		
	}
	
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
	
	public void recImg() throws IOException {
		
		
		final ReceiveImg ri = new ReceiveImg(indexPage.jbtArray);
		final Thread th1 = new Thread(new Runnable() {
				public void run() {
					ri.receive();
				} 
	    });
		th1.start();
		
		/*try {
			ServerSocket ss = new ServerSocket(SCREENPORT);
			Socket s;
			while((s = ss.accept()) != null){
				byte[] ip = s.getInetAddress().getAddress();
				System.out.println("连接成功");
				InputStream is = s.getInputStream();
				Image bi = ImageIO.read(is);
				if(bi == null){
					System.out.println("图片接受失败");
					continue;
				}
				bi = bi.getScaledInstance(320, 160, Image.SCALE_DEFAULT);//对图像进行压缩
				
				//ISMap.put(ip, bi);
				//System.out.println(ip);
				
				int index = ip[3];
				if(index == 5) index =0;
				
				//if(index >= 100) index -=140;
				//if(index >= 25) index -=25;
				ReciveImgThread rit = new ReciveImgThread(index, bi, indexPage.jbtArray);
				Thread t = new Thread(rit);
				t.start();
				System.out.println("接受成功");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void stopRecImg(){
		
	}
	
	public void sendMessage(String string) {
		for(String key:map.keySet()){
		    new SendMessage(string,map.get(key).getHostAddress(),MESSAGEPORT);
		    System.out.println(key +":"+ map.get(key));
		}
       //new SendMessage(string,"localhost",MESSAGEPORT);
	}
	
	public void sendFlie(){
		/*final SendFile sf = new SendFile(map);
		Thread th1 = new Thread(new Runnable() {
				public void run() {
					sf.send();
				} 
	    });*/
		//th1.start();
		//SendFile sf = new SendFile(map);
		//sf.send();
		/*for(String key:map.keySet()){
			new SendFile(map.get(key).getHostAddress(),FILEPORT);
		    //System.out.println(key +":"+ map.get(key));
		}*/
		new SendFile(FILEPORT,map);
	}

}
