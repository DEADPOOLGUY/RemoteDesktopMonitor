package Server;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.omg.CORBA.BAD_POLICY_TYPE;


public class Server{
	private String ip;
	private Socket socket;
    private ServerSocket serverSocket;
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private FileInputStream fin = null;
    private OutputStream os = null;
    private ByteArrayOutputStream bout = null;
	
	public Server(){
		
	}
	
	public void openConnection(){
	     try{
	    	socket = new Socket("localhost",8888);
	 		fromServer = new DataInputStream(socket.getInputStream());
	 		toServer = new DataOutputStream(socket.getOutputStream());
	 		
	 	    }
	 	 catch(IOException ex){
	 		ex.printStackTrace();
	 	    }
	}
	
	public void openServerConnection(){
	     try{
	    	serverSocket = new ServerSocket(8888);;
			socket = serverSocket.accept();
	 		fromServer = new DataInputStream(socket.getInputStream());
	 		toServer = new DataOutputStream(socket.getOutputStream());
	 		
	 	    }
	 	 catch(IOException ex){
	 		ex.printStackTrace();
	 	    }
	}
	
	public void closeConnection(){
		try {
			if(toServer != null)
			   toServer.close();
			if(fromServer != null)
			   fromServer.close();
			if(socket != null)
			   socket.close();
			if(serverSocket != null)
			   serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String string) {
        
	   try{
	       socket = new Socket("localhost",8889);
		   os =  socket.getOutputStream();
		   os.write(string.getBytes());
		   os.flush();
		   
	   
		   
		   
	   }
	   catch(IOException ex){
		System.err.println(ex);
	   }finally {
		   try {

			os.close();
			bout.close();
			closeConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	}
	
	public void sendFlie(){
		
		try{
	    	 
			   socket = new Socket("localhost",10000);
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
			try {
				if(fin != null)
				fin.close();				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			this.closeConnection();
			
		}
	
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}	

}
