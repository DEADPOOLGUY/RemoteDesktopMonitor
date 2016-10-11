package Server;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ReciveImgThread implements Runnable{
	int index;
	Image img;
	JButton[] jbtAry;
	public ReciveImgThread(int index, Image img, JButton[] jbtAry){
		this.index = index;
		this.img = img;
		this.jbtAry = jbtAry;
	}
	public void run(){
		jbtAry[index].setIcon(new ImageIcon(img));
	}
}
