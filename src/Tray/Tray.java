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

import ActionPerfomed.ActionPerformed;
import ClientFrame.ClientFrame;
import ClientFrame.SelecteFileFrame;
import Main.Main;

public class Tray {
	public Tray(){
		//判断是否支持系统托盘
		if(SystemTray.isSupported()){
			//获得当前系统的系统托盘
			SystemTray tray = SystemTray.getSystemTray();       
			//图标和工具栏
			Image img = Toolkit.getDefaultToolkit().getImage("image/tray.png");
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
							ActionPerformed sf1 = new ActionPerformed(e);
							Thread t = new Thread(sf1);
							t.start();
							//Main.sendFile(sf1);
				}
			});
			//
			menu.add(helpdoc);
			menu.add(sendFile);
			//menu.add(open);
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
