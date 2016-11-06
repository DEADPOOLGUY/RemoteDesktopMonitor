package Main;

import Broadcasting.BroadCasting;
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

import sendIP.SendIP;

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
		//
		ReciveMsg rm = new ReciveMsg();
		Thread rmt = new Thread(rm);
		rmt.start();
		//
		SendIP si = new SendIP();
		BroadCasting bc = new BroadCasting();
		
	}
}
