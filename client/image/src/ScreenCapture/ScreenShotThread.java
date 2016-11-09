package ScreenCapture;
/*
 * 不断截图和发送的线程类
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
		//不断截图，不断连接，不断发送
		while(true){
			ConnectServer cs = new ConnectServer("172.22.11.116", 8004);
			if(cs.isConnected()){
				Socket s = cs.getSocket();
				BufferedImage bi = sc.screenShot();
				try {
					OutputStream is = s.getOutputStream();
					ImageIO.write(bi, "JPEG", is);
					System.out.println("客户端发送图片成功");
					s.close();
					System.out.println("套接字是否还连接着" + s.isConnected());
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					System.out.println("获取套接字的输出流错误");
				}
			}
			else continue;
			//每隔100ms就截图发送
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
