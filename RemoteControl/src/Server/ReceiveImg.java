package Server;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.*;

import javax.swing.*;
import javax.imageio.ImageIO;

public class ReceiveImg {
	
    private int port = 8887;//屏幕监控服务器端口为8887
    private ServerSocket serverSocket;
    JButton[] jButtons;
    int w;
    int h;
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(6);//建立可容纳6个线程的线程池
    
    public ReceiveImg(JButton[] jButtons) {
    	try{
    		this.jButtons = jButtons;
        	serverSocket = new ServerSocket(port);
        	System.out.println("屏幕监控服务器启动");

    	}catch(IOException exception){
    		exception.printStackTrace();
    	}finally{
        	w = (int)(4*jButtons[0].getWidth()/5);//规定每个屏幕监控功能的显示按钮的宽度
        	h = (int)(4*jButtons[0].getHeight()/5);//规定每个屏幕监控功能的显示按钮的长度
    	}

    }
    
	/*public void receive(){
		while (true) {
			Socket socket = null;
			try{
				socket = serverSocket.accept();
				byte[] ip = socket.getInetAddress().getAddress();
				System.out.println("连接成功");
				InputStream is = socket.getInputStream();
				Image bi = ImageIO.read(is);
				if(bi == null){
					System.out.println("图片接受失败");
					continue;
				}
				//System.out.println(jButtons[0].getWidth()+ "+" +jButtons[0].getHeight());
				bi = bi.getScaledInstance(w, h, Image.SCALE_DEFAULT);//对图像进行压缩
				//bi = bi.getScaledInstance(218, 160, Image.SCALE_DEFAULT);
				int index = 0;
				//int index = ip[3];
				//if(index == 5) index =0;
				
				//if(index >= 100) index -=140;
				//if(index >= 25) index -=25;
				Thread work = new Thread(new Handler(index, bi, jButtons));
				work.start();
				
				System.out.println("接受成功");
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
	}*/
    public void stop(){
    	try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void receive(){
		while (true) {
			Socket socket = null;
			try{
				socket = serverSocket.accept();
				
				Thread work = new Thread(new Handler(socket, jButtons));
				fixedThreadPool.execute(work);

				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
    
    class Handler implements Runnable{
        JButton[] jButtons;
        Socket socket;
        
        public Handler(Socket socket,JButton[] jButtons) {
        	 this.jButtons = jButtons;
			 this.socket = socket;
	         
       }
        
       public void run(){
    	   try{
			String ip = socket.getInetAddress().getHostAddress();
			int sindex = ip.lastIndexOf('.');
		    ip = ip.substring(sindex+1);
		    
			System.out.println("连接成功");
			InputStream is = socket.getInputStream();
			Image bi = ImageIO.read(is);
			if(bi == null){
				System.out.println("图片接受失败");
				//continue;
			}
			//System.out.println(jButtons[0].getWidth()+ "+" +jButtons[0].getHeight());
			bi = bi.getScaledInstance(w, h, Image.SCALE_DEFAULT);//对图像进行压缩
			//bi = bi.getScaledInstance(218, 160, Image.SCALE_DEFAULT);
			
			int index =  Integer.valueOf(ip).intValue();
			//int index = ip[3]+1;
			//if(index == 5) index =0;
			
			if(index >= 100) index -=110;
			//if(index >= 25) index -=24;
			
			System.out.println("接受成功");
	        jButtons[index].setIcon(new ImageIcon(bi));
    	   }catch(IOException e){
    		   e.printStackTrace();
    	   }
       }
       
       
       
   }
	
    /*class Handler implements Runnable{
         int index;
         Image img;
         JButton[] jbtAry;
         
         public Handler(int index, Image img, JButton[] jbtArray) {
			
	         this.index = index;
	         this.img = img;
	         this.jbtAry = jbtArray;
	         
        }
         
        public void run(){
	        jbtAry[index].setIcon(new ImageIcon(img));
        }
        
        
        
    }*/
}
	/*public static void getImg(JButton[] jbtArray){
		try {
			ServerSocket ss = new ServerSocket(8888);
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
				ReciveImgThread rit = new ReciveImgThread(index, bi, jbtArray);
				Thread t = new Thread(rit);
				t.start();
				System.out.println("接受成功");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	}
    
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Map<String, Image> ISMap = new HashMap<String, Image>();
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setBounds(200, 300, 640, 360);
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(2,2));
		jf.add(jp);
		JButton[] jbtArray = new JButton[4];
		int i = 0; 
		for(i = 0; i < 4; i++){
			jbtArray[i] = new JButton();
			jp.add(jbtArray[i]);
		}
		try {
			ServerSocket ss = new ServerSocket(8888);
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
				ReciveImgThread rit = new ReciveImgThread(index, bi, jbtArray);
				Thread t = new Thread(rit);
				t.start();
				System.out.println("接受成功");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/


