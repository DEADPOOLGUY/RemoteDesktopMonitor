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

import javax.sound.midi.ShortMessage;

import ActionPerfomed.ActionPerformed;
import ClientFrame.ClientFrame;
import ClientFrame.SelecteFileFrame;
import FileReciveAndSend.SendFile;
import Main.Main;
import MsgReciveAndSend.SendMsg;

public class Tray {
	public Tray(){
		//�ж��Ƿ�֧��ϵͳ����
		if(SystemTray.isSupported()){
			//��õ�ǰϵͳ��ϵͳ����
			SystemTray tray = SystemTray.getSystemTray();       
			//ͼ��͹�����
			Image img = Toolkit.getDefaultToolkit().getImage("image/tray.png");
			PopupMenu menu = new PopupMenu();//popupmenu:ͼƬ����ʽ�˵�
			//�˵�����
			MenuItem helpdoc = new MenuItem("�����ĵ�");
			helpdoc.setActionCommand("helpdoc");
			MenuItem sendMessage = new MenuItem("������Ϣ");
			sendMessage.setActionCommand("sendMessage");
			MenuItem exit = new MenuItem("�˳�");
			MenuItem sendFile = new MenuItem("�����ļ�");
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
					// TODO �Զ����ɵķ������
					SendMsg sm = new SendMsg("192.168.1.128");
				}
				
			});
			//
			menu.add(helpdoc);
			menu.add(sendFile);
			menu.add(sendMessage);
			menu.add(exit);
			TrayIcon trayIcon = new TrayIcon(img, "Զ��������ϵͳ", menu);
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
