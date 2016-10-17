package Main;
import ActionPerfomed.ActionPerformed;
import ConnectServer.ConnectServer;
import FileReciveAndSend.ReciveFile;
import FileReciveAndSend.SendFile;
import ReciveMsg.ReciveMsg;
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
		Thread rfThread = new Thread(rf);
		rfThread.start();
		Socket sm;
		try {
			sm = new Socket("172.22.11.116",8886);
		} catch (UnknownHostException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		//
		ReciveMsg rm = new ReciveMsg();
		rm.setVisible(false);
		try {
			ServerSocket ss = new ServerSocket(8889);
			Socket s;
			while((s = ss.accept())!= null){
				if(!rm.isActive()) rm = new ReciveMsg();
				InputStream is = s.getInputStream();  
                String msg = "老师" +":";  
                byte[] buf = new byte[1024*8]; 
                for(int len=is.read(buf);len>0;len=is.read(buf)){ 
                msg+=new String(buf, 0, len);
                rm.setMsg(msg);
                rm.setVisible(true);
                }
			}
		}catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
