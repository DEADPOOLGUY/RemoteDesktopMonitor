package FileReciveAndSend;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ClientFrame.SelecteFileFrame;
import ConnectServer.ConnectServer;

public class SendFile {
	public SendFile(){
		ConnectServer cs = new ConnectServer("172.22.11.116",8888);
		Socket s = cs.getSocket();
		SelecteFileFrame sff = new SelecteFileFrame();
		File file = sff.getPath();
		int length;
	    DataOutputStream toServer = null;
	    FileInputStream fis = null;
		if(file != null){
			OutputStream os;
			try {
				os = s.getOutputStream();
				toServer = new DataOutputStream(os);
		    	fis = new FileInputStream(file);
		    	byte[] sendByte = new byte[1024];
		    	toServer.writeUTF(file.getName());
		    	while((length = fis.read(sendByte, 0, sendByte.length))>0){
		    		toServer.write(sendByte, 0, length);
		    		toServer.flush();
		    	}
		    	JOptionPane.showMessageDialog(null, "文件发送成功！", "提示", JOptionPane.DEFAULT_OPTION);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			finally{
				try{
					if(fis != null)
					       fis.close();
					    if(toServer != null)
						   toServer.close();
						if(s != null)
						   s.close();
				}
				catch (Exception e){
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}	    	
		}
}
