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
			// TODO 自动生成的 catch 块
			System.out.println("IP端口申请错误");
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
		// TODO 自动生成的方法存根
		while(stop){
			try {
				Socket imgs;
				while((imgs = imgss.accept()) != null){
					System.out.println("8885接收到连接");
					i = ImageIO.read(imgs.getInputStream());
					if(i != null){
						i = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
						System.out.println("接受成功");
						jb.setIcon(new ImageIcon(i));
					}
					else{
						System.out.println("接受失败");
					}
				}
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			System.out.println("程序末端");
		}
		
	}
	public void setBol(boolean stop){
		this.stop = stop;
		if(stop == false) jf.dispose();
	}
}
