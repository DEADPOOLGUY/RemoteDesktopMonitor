package ActionPerfomed;

import java.awt.event.*;
import java.io.*;
import java.net.Socket;

import ClientFrame.HelpDoc;
import ClientFrame.SelecteFileFrame;

public class ActionPerformed implements Runnable{
	String actionCMD;
	public ActionPerformed(ActionEvent e){
		actionCMD = e.getActionCommand();
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(actionCMD.equals("exit")){
			System.exit(0);
		}
		//发送文件功能
		else if(actionCMD.equals("sendFile")){
			SelecteFileFrame sff = new SelecteFileFrame();
			File path = sff.getPath();
			if(path != null || path.isFile()){
				String fileName = path.getName();
				byte[] content;
				try {
					//用文件输入流将文件内容写到数组
					FileInputStream fis = new FileInputStream(path);
					content = new byte[fis.available()];
					fis.read(content);
					sendingFile sf = new sendingFile(fileName, content);
					//连接服务器
					Socket sfs = new Socket("localhost", 8888);
					System.out.println("连接成功");
					OutputStream os = sfs.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeObject(sf);
					System.out.println("send file successfully");
					oos.flush();
					sfs.close();
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch(IOException e1){
					System.out.println("连接失败");
				}
			}
		}
		if(actionCMD.equals("helpdoc")){
			HelpDoc hd = new HelpDoc();
		}
		else if(actionCMD.equals("sendMessage")){
			
		}
	}
}

class sendingFile implements Serializable{
	static final long serializVersionUID = 123456L;
	String fileName;
	byte[] content;
	public sendingFile(String fileName, byte[] content){
		 this.fileName = fileName;
		 this.content = content;
	}
}
