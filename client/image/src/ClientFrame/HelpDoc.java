package ClientFrame;
/*
 *�����ĵ����� 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class HelpDoc{
	//���췽��
	public HelpDoc(){}
	//���ذ����ĵ�����
	public static JFrame helpDoc(){
		//��������
		JFrame helpDoc = new JFrame();
		//����
		helpDoc.setTitle("�����ĵ�");
		helpDoc.setVisible(true);
		//�õ����ڸ߶ȺͿ��
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