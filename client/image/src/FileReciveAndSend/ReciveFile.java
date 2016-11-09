package FileReciveAndSend;
/*
 * 接收文件的类，继承了Runnable接口
 */
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ReciveFile implements Runnable {
	
	public ReciveFile(){
		
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			ServerSocket ss = new ServerSocket(8001);
			Socket s;
			//不断检测教师端是否有连接
			while((s =ss.accept()) != null){
				System.out.println("文件接收端口有连接");
				//将文件内容先放到数组
				byte[] inputByte = null;
		        int length = 0;
		        DataInputStream din = null;
		        //用于文件输出的流
		        FileOutputStream fout = null;
		        try {
		            din = new DataInputStream(s.getInputStream());
		            //保存到D盘
		            fout = new FileOutputStream(new File("D:\\"+din.readUTF()));
		            inputByte = new byte[1024];
		            System.out.println("开始接收数据...");
		            //接收数据
		            while (true) {
		                if (din != null) {
		                    length = din.read(inputByte, 0, inputByte.length);
		                }
		                if (length == -1) {
		                    break;
		                }
		                System.out.println(length);
		                fout.write(inputByte, 0, length);
		                fout.flush();
		            }
		            //提示用户接收到文件
		            JOptionPane.showMessageDialog(null, "成功接收文件，已存放于D盘！", "提示", JOptionPane.DEFAULT_OPTION);
		            System.out.println("完成接收");
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        } finally {
		            if (fout != null)
		                fout.close();
		            if (din != null)
		                din.close();
		            if (s != null)
		                s.close();
		        }
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
