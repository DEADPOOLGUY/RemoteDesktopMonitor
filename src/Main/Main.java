package Main;
import ActionPerfomed.ActionPerformed;
import ConnectServer.ConnectServer;
import FileReciveAndSend.ReciveFile;
import FileReciveAndSend.SendFile;
import MsgReciveAndSend.ReciveMsg;
import ScreenCapture.ScreenShotThread;
import ScreenCapture.screenCapture;
import Tray.Tray;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tray tray = new Tray();
		ScreenShotThread sst = new ScreenShotThread();
		Thread sstThread = new Thread(sst);
		sstThread.start();
		ReciveFile rf = new ReciveFile();
		/*Thread t1 = new Thread(new Runnable(){
			public void run(){ReciveFile rf = new ReciveFile();}
		});
		t1.start();*/
		Thread rfThread = new Thread(rf);
		rfThread.start();
		Socket sm;
		try {
			while((sm = new Socket("172.18.34.58",8886)) != null){
				System.out.println("连接成功");
				break;
			}
		} catch (UnknownHostException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		//
		ReciveMsg rm = new ReciveMsg();
	}
}
