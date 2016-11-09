package ScreenCapture;
/*
 * 用于截图的类
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
	//获取封装屏幕宽度、高度的Dimension对象
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	//用于返回的图片
	BufferedImage screenshot;
	/*
	 * 线程同步的截屏方法
	 * 返回图片
	 */
	public synchronized BufferedImage screenShot() {
		try {
			screenshot = (new Robot()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (HeadlessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return screenshot;
	}
	/*//返回截到的图片
		public int[][] convertImageToArray(BufferedImage img){
			//图片的长宽高
			int width = img.getWidth();
			int height = img.getHeight();
			// 将图片sRGB数据写入一维数组
			int[] data = new int[width*height];
			data = img.getRGB(0, 0, width, height, data, 0, width);
			// 将一维数组转换为为二维数组
			int[][] rgbArray = new int[height][width];
			for(int i = 0; i < height; i++)
		    for(int j = 0; j < width; j++)
			rgbArray[i][j] = data[i*width + j];
			return rgbArray;
		}*/
}
