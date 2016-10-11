package Server;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.*;

import javax.swing.*;
import javax.imageio.ImageIO;

public class ReciveImg {
    public ReciveImg(){
    	
    }
	/**
	 * @param args
	 */
	public static void getImg(JButton[] jbtArray){
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


