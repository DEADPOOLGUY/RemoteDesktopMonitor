package MsgReciveAndSend;
/*
 * 接收消息类
 */
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.io.InputStream;

public class ReciveMsg implements Runnable{
    public ReciveMsg(){
    	
    }
	//接收消息的线程 在后天运行
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			System.out.println("接收消息线程开始");
			ServerSocket ss = new ServerSocket(8003);      
			Socket s;
			//循环监听教师端的连接
			while(true){
				s = ss.accept();
				System.out.println("接受到服务端的消息连接");
				InputStream is = s.getInputStream();
				byte[] buf = new  byte[1024*8];
				//封装消息的字符串
				String msg = "";
				//读取消息内容
				for(int len=is.read(buf);len>0;len=is.read(buf)){ 
				//int len = is.read(buf);
				msg += new String(buf, 0, len);
	                }
				//判断是否为关机或者黑屏命令
				if(msg.equals("sleep")){
					Runtime rt = Runtime.getRuntime();
					Process p = rt.exec("rundll32.exe powrProf.dll, SetSuspendState");
					JOptionPane.showConfirmDialog(null, "您的计算机受到远程黑屏控制，计算机将在60s后睡眠，请保存好文件！", "提示", JOptionPane.DEFAULT_OPTION);
				}else if(msg.equals("shutdown")){
					Runtime rt = Runtime.getRuntime();
					JOptionPane.showConfirmDialog(null, "您的计算机受到远程黑屏控制，计算机将在60s后睡眠，请保存好文件！", "提示", JOptionPane.DEFAULT_OPTION);
					Process p = rt.exec("shutdown -s -t 0");
				}
				else{
					JOptionPane.showMessageDialog(null, msg, "来自老师的消息", JOptionPane.DEFAULT_OPTION);
				}
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
