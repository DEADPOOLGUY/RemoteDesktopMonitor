package Tray;
/*
 * ϵͳ������
 */
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

import ClientFrame.HelpDoc;
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
			
			InputStream is = this.getClass().getResourceAsStream("/image/tray.png");//Ϭ����
			Image img = null;
			try {
				img = ImageIO.read(is);
			} catch (IOException e2) {
				// TODO �Զ����ɵ� catch ��
				e2.printStackTrace();
			}
			
			PopupMenu menu = new PopupMenu();//popupmenu:ͼƬ����ʽ�˵�
			//�˵�����
			MenuItem helpdoc = new MenuItem("�����ĵ�");
			helpdoc.setActionCommand("helpdoc");
			MenuItem sendMessage = new MenuItem("������Ϣ");
			sendMessage.setActionCommand("sendMessage");
			MenuItem exit = new MenuItem("�˳�");
			MenuItem sendFile = new MenuItem("�����ļ�");
			sendFile.setActionCommand("sendFile");
			//Ϊ�����ĵ�����¼�
			helpdoc.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					HelpDoc.helpDoc();
				}
				
			});
			//Ϊ�˳���ť����¼�
			exit.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.exit(-1);
				}
				
			});
			//Ϊ�����ļ���ť����¼�
			sendFile.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
							SendFile sf = new SendFile();
				}
			});
			sendMessage.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO �Զ����ɵķ������
					SendMsg sm = new SendMsg();
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
