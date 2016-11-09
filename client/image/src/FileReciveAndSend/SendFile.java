package FileReciveAndSend;
/*
 * 用于文件发送的类
 */
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ClientFrame.SelecteFileFrame;
import ConnectServer.ConnectServer;

public class SendFile {
	public SendFile(){
			Socket s;
			//连接教师端
			try {
				s = new Socket("172.22.11.116",8006);
				System.out.println("连接服务器文件端口成功");
				//自定义的文件选择窗口
				SelecteFileFrame sff = new SelecteFileFrame();
				File file = sff.getPath();
				int length;
			    DataOutputStream toServer = null;
			    FileInputStream fis = null;
			    //判断是否选择到文件
			    if(file != null){
					OutputStream os;
					try {
						//开始将文件内容写入到套接字的输出流
						os = s.getOutputStream();
						toServer = new DataOutputStream(os);
				    	fis = new FileInputStream(file);
				    	byte[] sendByte = new byte[1024];
				    	toServer.writeUTF(file.getName());
				    	while((length = fis.read(sendByte, 0, sendByte.length))>0){
				    		toServer.write(sendByte, 0, length);
				    		toServer.flush();
				    	}//while
				    	JOptionPane.showMessageDialog(null, "文件发送成功！", "提示", JOptionPane.DEFAULT_OPTION);
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}//catch
			    }//if file
			}catch(SocketException e){
				//连接失败的提示
				JOptionPane.showMessageDialog(null, "连接服务器超时", "错误消息", JOptionPane.DEFAULT_OPTION);
			} catch (UnknownHostException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
	}
}
		
