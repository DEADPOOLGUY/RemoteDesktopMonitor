package ScreenCapture;
/*
 * ���ڽ�ͼ����
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import ConnectServer.ConnectServer;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class screenCapture {
	//��ȡ��װ��Ļ��ȡ��߶ȵ�Dimension����
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	//���ڷ��ص�ͼƬ
	BufferedImage screenshot;
	/*
	 * �߳�ͬ���Ľ�������
	 * ����ͼƬ
	 */
	public synchronized BufferedImage screenShot() {
		try {
			screenshot = (new Robot()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (HeadlessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return screenshot;
	}
	/*//���ؽص���ͼƬ
		public int[][] convertImageToArray(BufferedImage img){
			//ͼƬ�ĳ����
			int width = img.getWidth();
			int height = img.getHeight();
			// ��ͼƬsRGB����д��һά����
			int[] data = new int[width*height];
			data = img.getRGB(0, 0, width, height, data, 0, width);
			// ��һά����ת��ΪΪ��ά����
			int[][] rgbArray = new int[height][width];
			for(int i = 0; i < height; i++)
		    for(int j = 0; j < width; j++)
			rgbArray[i][j] = data[i*width + j];
			return rgbArray;
		}*/
}
