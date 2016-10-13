package FileReciveAndSend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;

import javax.swing.JFileChooser;

import ClientFrame.SelecteFileFrame;
import ConnectServer.ConnectServer;

public class SendFile {
	public SendFile(){
		ConnectServer cs = new ConnectServer("172.22.13.148",8882);
		Socket s = cs.getSocket();
		SelecteFileFrame sff = new SelecteFileFrame();
		File path = sff.getPath();
		try {
			FileInputStream fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}
}
