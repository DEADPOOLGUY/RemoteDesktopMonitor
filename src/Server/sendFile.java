package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class sendFile {
	private Socket socket;
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private FileInputStream fin = null;

    public  sendFile(String ip,int port){
	try{
   	 
		   socket = new Socket(ip,port);
		   fromServer = new DataInputStream(socket.getInputStream());
	       toServer = new DataOutputStream(socket.getOutputStream());
	       int length = 0;
	       byte[] sendByte = null;
		   JFileChooser fileChooser = new JFileChooser();
	       
	       if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	    	java.io.File file = fileChooser.getSelectedFile();
	    	fin = new FileInputStream(file);
	    	sendByte = new byte[1024];
	    	toServer.writeUTF(file.getName());
	    	while((length = fin.read(sendByte, 0, sendByte.length))>0){
	    		toServer.write(sendByte, 0, length);
	    		toServer.flush();
	    	}
	    	JOptionPane.showMessageDialog(null, "文件发送成功！", "提示", JOptionPane.DEFAULT_OPTION);	    	
	       }
	       
	}
	catch(Exception ex){
		ex.printStackTrace();
	}finally {
		try  {
			    if(fin != null)
			       fin.close();
			    if(toServer != null)
				   toServer.close();
				if(fromServer != null)
				   fromServer.close();
				if(socket != null)
				   socket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		
	}
   }
}
