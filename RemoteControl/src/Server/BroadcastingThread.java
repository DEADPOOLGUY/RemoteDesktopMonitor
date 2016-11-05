package Server;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.io.OutputStream;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
public 







class BroadcastingThread implements Runnable{
	BufferedImage img;
	Robot r;
	Toolkit t;
	Rectangle rec;
	Image i;
	Socket s;
	boolean stop;
	public BroadcastingThread(){
		try {
			r = new Robot();
			t = Toolkit.getDefaultToolkit();
			rec = new Rectangle(t.getScreenSize());
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		System.out.println("直播线程开始");
		while(stop){
			// TODO Auto-generated method stub
			try {
				Socket s = new Socket("localhost", 8885);
				System.out.println("连接8885成功");
				i = r.createScreenCapture(rec);
				if(i != null){
					ImageIO.write((RenderedImage) i, "JPEG", s.getOutputStream());
					s.getOutputStream().flush();
					System.out.println("截图发送成功");
				}
				else System.out.println("截图获取失败");
				s.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}//run
	public void setBol(boolean stop){
		this.stop = stop;
	}
	
}
