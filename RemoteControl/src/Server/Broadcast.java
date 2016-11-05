package Server;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

public class Broadcast {
    public Broadcast(){
    	Socket s;
		try {
			System.out.println("检测到动作事件");
			s = new Socket("localhost", 8884);
			System.out.println("连接成功");
			BroadcastingThread bct = null;
			s.getOutputStream().write(click_num % 2);
			s.getOutputStream().flush();
			if(click_num % 2 == 1){
				bct = new BroadcastingThread();
				bct.setBol(true);      //设置开始循环标志
				t = new Thread(bct);   //线程
				t.start();             //线程开始
				System.out.println("开始直播");
			}
			else{
				bct.setBol(false);     //退出循环
				//sct.dispose();         //s
				t.stop();             //销毁线程
			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}      //发送控制信息的套接字
	}
    }
}
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
