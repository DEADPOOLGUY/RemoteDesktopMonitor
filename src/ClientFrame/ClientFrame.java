package ClientFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ClientFrame{
	//构造方法
	public ClientFrame(){}
	//返回帮助文档窗口
	public static JFrame HelpDoc(){
		//创建窗口
		JFrame helpDoc = new JFrame();
		//属性
		helpDoc.setTitle("帮助文档");
		helpDoc.setVisible(true);
		//得到窗口高度和宽度
		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int positionX = screenWidth * 3 / 8;
		int positionY = screenHeight * 3 / 8;
		int width = screenWidth / 4;
		int height = screenHeight / 4;
		helpDoc.setBounds(positionX, positionY, width, height);
		return helpDoc;
	}
}