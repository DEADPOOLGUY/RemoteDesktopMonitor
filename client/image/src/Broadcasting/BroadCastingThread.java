package Broadcasting;
import java.net.Socket;
import java.net.ServerSocket;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
public class BroadCastingThread implements Runnable {
	JFrame jf;
	Image i;
	int width;
	int height;
	JButton jb;
	boolean stop;
	ServerSocket imgss;
	public BroadCastingThread(){
		
		this.jb = new JButton();
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4;
		
		try {
			imgss = new ServerSocket(8885);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("IP�˿��������");
		}
	}
	@Override
	public void run() {
		this.jf = new JFrame();
		jf.setUndecorated(true);
		jf.setLayout(null);
		jf.setBounds(0, 0, width, height);
		jb.setBounds(0, 0, width, height);
		jf.add(jb);
		//jf.setVisible(false);
		jf.setVisible(true);
		jf.setAlwaysOnTop(true);
		// TODO �Զ����ɵķ������
		while(stop){
			try {
				Socket imgs;
				while((imgs = imgss.accept()) != null){
					System.out.println("8885���յ�����");
					i = ImageIO.read(imgs.getInputStream());
					if(i != null){
						i = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
						System.out.println("���ܳɹ�");
						jb.setIcon(new ImageIcon(i));
					}
					else{
						System.out.println("����ʧ��");
					}
				}
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			System.out.println("����ĩ��");
		}
		
	}
	public void setBol(boolean stop){
		this.stop = stop;
		if(stop == false) jf.dispose();
	}
}
