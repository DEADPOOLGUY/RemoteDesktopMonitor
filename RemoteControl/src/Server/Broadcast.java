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
			System.out.println("��⵽�����¼�");
			s = new Socket("localhost", 8884);
			System.out.println("���ӳɹ�");
			BroadcastingThread bct = null;
			s.getOutputStream().write(click_num % 2);
			s.getOutputStream().flush();
			if(click_num % 2 == 1){
				bct = new BroadcastingThread();
				bct.setBol(true);      //���ÿ�ʼѭ����־
				t = new Thread(bct);   //�߳�
				t.start();             //�߳̿�ʼ
				System.out.println("��ʼֱ��");
			}
			else{
				bct.setBol(false);     //�˳�ѭ��
				//sct.dispose();         //s
				t.stop();             //�����߳�
			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}      //���Ϳ�����Ϣ���׽���
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
		System.out.println("ֱ���߳̿�ʼ");
		while(stop){
			// TODO Auto-generated method stub
			try {
				Socket s = new Socket("localhost", 8885);
				System.out.println("����8885�ɹ�");
				i = r.createScreenCapture(rec);
				if(i != null){
					ImageIO.write((RenderedImage) i, "JPEG", s.getOutputStream());
					s.getOutputStream().flush();
					System.out.println("��ͼ���ͳɹ�");
				}
				else System.out.println("��ͼ��ȡʧ��");
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
