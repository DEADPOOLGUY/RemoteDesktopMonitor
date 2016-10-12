package Server;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class ReciveImgThread implements Runnable{
	int index;
	Image img;
	JButton[] jbtAry;
	public ReciveImgThread(int index, Image img, JButton[] jbtArray){
		this.index = index;
		this.img = img;
		this.jbtAry = jbtArray;
	}
	public void run(){
		jbtAry[index].setIcon(new ImageIcon(img));
	}
}
