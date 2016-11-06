package Tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.midi.ShortMessage;
import javax.swing.ImageIcon;

import ClientFrame.ClientFrame;
import ClientFrame.SelecteFileFrame;
import FileReciveAndSend.SendFile;
import Main.Main;
import MsgReciveAndSend.SendMsg;

public class Tray {
	public Tray(){
		//判断是否支持系统托盘
		if(SystemTray.isSupported()){
			//获得当前系统的系统托盘
			SystemTray tray = SystemTray.getSystemTray();       
			//图标和工具栏
			//URL imgURL1 = Tray.class.getResource("/tray.png"); 
			//ImageIcon img = new ImageIcon(imgURL1); 
			//Image img = Toolkit.getDefaultToolkit().getImage("image/tray.png");
			InputStream is = this.getClass().getResourceAsStream("/image/tray.png");//犀利！
			Image img = null;
			try {
				img = ImageIO.read(is);
			} catch (IOException e2) {
				// TODO 自动生成的 catch 块
				e2.printStackTrace();
			}
			/*BufferedImage img = null;
			try {
				img = ImageIO.read(Tray.class.getResourceAsStream("/tray.png"));
			} catch (IOException e2) {
				// TODO 自动生成的 catch 块
				e2.printStackTrace();
			}*/
			PopupMenu menu = new PopupMenu();//popupmenu:图片弹出式菜单
			//菜单内容
			MenuItem helpdoc = new MenuItem("帮助文档");
			helpdoc.setActionCommand("helpdoc");
			MenuItem sendMessage = new MenuItem("发送消息");
			sendMessage.setActionCommand("sendMessage");
			MenuItem exit = new MenuItem("退出");
			MenuItem sendFile = new MenuItem("发送文件");
			sendFile.setActionCommand("sendFile");
			//
			helpdoc.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ClientFrame.HelpDoc();
				}
				
			});
			exit.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.exit(-1);
				}
				
			});
			sendFile.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					/*SelecteFileFrame sf = new SelecteFileFrame();
					File path = sf.getPath();
					if(path != null || path.isFile()){
						String fileName = path.getName();
						byte[] content;
						try {
							FileInputStream fis = new FileInputStream(path);
							content = new byte[fis.available()];
							fis.read(content);*/
							SendFile sf = new SendFile();
				}
			});
			sendMessage.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO 自动生成的方法存根
					SendMsg sm = new SendMsg();
				}
				
			});
			//
			menu.add(helpdoc);
			menu.add(sendFile);
			menu.add(sendMessage);
			menu.add(exit);
			TrayIcon trayIcon = new TrayIcon(img, "远程桌面监控系统", menu);
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
