package ScreenCapture;
/*
 * ���Ͻ�ͼ�ͷ��͵��߳���
 */
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

import ConnectServer.ConnectServer;

public class ScreenShotThread implements Runnable {
	Socket socket;
	screenCapture sc;
	public ScreenShotThread(){
		sc = new screenCapture();
	}
	public void run() {
		//���Ͻ�ͼ���������ӣ����Ϸ���
		while(true){
			ConnectServer cs = new ConnectServer("172.22.11.116", 8004);
			if(cs.isConnected()){
				Socket s = cs.getSocket();
				BufferedImage bi = sc.screenShot();
				try {
					OutputStream is = s.getOutputStream();
					ImageIO.write(bi, "JPEG", is);
					System.out.println("�ͻ��˷���ͼƬ�ɹ�");
					s.close();
					System.out.println("�׽����Ƿ�������" + s.isConnected());
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					System.out.println("��ȡ�׽��ֵ����������");
				}
			}
			else continue;
			//ÿ��100ms�ͽ�ͼ����
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
