package ScreenCapture;

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
		while(true){
			ConnectServer cs = new ConnectServer("192.168.1.128", 8887);
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
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
