package Server;

import java.awt.Image;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import org.omg.CORBA.PUBLIC_MEMBER;

import GUI.IndexPage;


public class Server{
	static int SCREENPORT = 8887;
	static int FILEPORT = 8888;
	static int MESSAGEPORT = 8889;
	
	IndexPage indexPage;
	
	public Server(){
		indexPage = new IndexPage(this);
	}
	
	/*public static void main(String[] args) {
		 
	     new Server();
	     
	}*/
	
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
				bi = bi.getScaledInstance(320, 180, Image.SCALE_DEFAULT);//对图像进行压缩
				//ISMap.put(ip, bi);
				//System.out.println(ip);
				
				int index = ip[3];
				if(index >= 100) index -=100;
				if(index >= 25) index -=25;
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
       new SendMessage(string,"localhost",MESSAGEPORT);
	}
	
	public void sendFlie(){
       new SendFile("localhost",FILEPORT);
	}

}
