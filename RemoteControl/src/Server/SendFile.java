package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SendFile {
	private Socket socket;
    private DataOutputStream toServer;
    private FileInputStream fin = null;

    public  SendFile(int port,HashMap<String,InetAddress> map){
	
    	try{
	       int length = 0;
	       byte[] sendByte = null;
		   JFileChooser fileChooser = new JFileChooser();
	       
	       if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	    	java.io.File file = fileChooser.getSelectedFile();//文件选择
			 for(String key:map.keySet()){//根据传入的存储用户ip信息的hashmap实现循环发送			
			    socket = new Socket(map.get(key).getHostAddress(),port);
		        toServer = new DataOutputStream(socket.getOutputStream());//初始化数据输出流
	    	    fin = new FileInputStream(file);//初始化文件输入流
	    	    sendByte = new byte[1024];
	    	    toServer.writeUTF(file.getName());
	    	    while((length = fin.read(sendByte, 0, sendByte.length))>0){//实现文件发送
	    		      toServer.write(sendByte, 0, length);
	    		      toServer.flush();
	    	    }
	    	
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
				if(socket != null)
				   socket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		
	}
   }
}
/*public class SendFile{
	HashMap<String,InetAddress> map = new HashMap<String,InetAddress>();
	private Socket socket;
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private FileInputStream fin = null;
	
	public SendFile(HashMap<String,InetAddress> map){
		this.map = map;
	}
	
	public void send(){
		
		final JFileChooser fileChooser = new JFileChooser();
		Thread th1 = new Thread(new Runnable() {
			public void run() {
				for(String key:map.keySet()){

					try{
						   
						   socket = new Socket(map.get(key).getHostAddress(),8888);
						   fromServer = new DataInputStream(socket.getInputStream());
					       toServer = new DataOutputStream(socket.getOutputStream());
					       int length = 0;
					       byte[] sendByte = null;
					       
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
					}
				}
			} 
		});
		th1.start();
		
	}
}*/


