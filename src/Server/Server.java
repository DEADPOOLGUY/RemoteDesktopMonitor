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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTextArea;

import org.omg.CORBA.PUBLIC_MEMBER;

import GUI.IndexPage;


public class Server{
	static int SCREENPORT = 8887;
	static int FILEPORT = 8888;
	static int MESSAGEPORT = 8889;
	HashMap<String, InetAddress> map = new HashMap<>();
	static IndexPage indexPage;
	
	public Server(){
		indexPage = new IndexPage(this);
	}
	
	public static void main(String[] args) {
		 
	     Server server = new Server();
	     //server.recImg();
	     //server.listenerForClients();
	     
	}
	
	public void listenerForClients(){

		try{
			ServerSocket serverSocket = new ServerSocket(8000);
			indexPage.jta1.append("Multi...Server started at " + new Date() + '\n');
			
			int clientNo = 1;
			String key = "";
			while(true){
				Socket socket = serverSocket.accept();
				InetAddress inetAddress = socket.getInetAddress();
				key = String.valueOf(clientNo);
				map.put(key, inetAddress);
				MutliJtaThread mjt = new MutliJtaThread(indexPage.jta1,clientNo,inetAddress);
				Thread t = new Thread(mjt);
				t.start();
				clientNo++;
			}
		}
		catch(IOException exception){
			System.err.println(exception);
		}
	} 
	
	public void recImg() {
		try {
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
		}
	}
	
	public void sendMessage(String string) {
       new SendMessage(string,"172.22.5.5",MESSAGEPORT);
       //new SendMessage(string,"172.22.12.3",MESSAGEPORT);
	}
	
	public void sendFlie(){
       new SendFile("172.22.5.5",FILEPORT);
       //new SendFile("172.22.12.3",FILEPORT);
	}

}
