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
		Thread  thRec = new Thread(new Runnable() {
				public void run() {
					ri.receive();
				} 
	    });
		thRec.start();
	
		
	}
		
	public void stopRecImg(){       
		System.out.println("");
	}
	
	public void sendShutDown() {		
		    new SendMessage("shutdown",map,MESSAGEPORT);
	}	
	
	public void sendSleep() {		
	    new SendMessage("sleep",map,MESSAGEPORT);
    }
	
	public void sendMessage(String string) {	
		    new SendMessage(string,map,MESSAGEPORT);	
	}
	
	public void sendFlie(){
		 	new SendFile(FILEPORT,map);
	}

}
