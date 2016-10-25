package Server;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class MultiThreadServer {
    private HashMap<String, InetAddress> map = new HashMap<>();
	public MultiThreadServer(JTextArea jta){
		
		try{
			ServerSocket serverSocket = new ServerSocket(8000);
			jta.append("Multi...Server started at " + new Date() + '\n');
			
			int clientNo = 1;
			String key = "";
			while(true){
				Socket socket = serverSocket.accept();
				InetAddress inetAddress = socket.getInetAddress();
				key = String.valueOf(clientNo);
				map.put(key, inetAddress);
				jta.append("Client " + clientNo + "'s host name is " + inetAddress.getHostName() + '\n');
				jta.append("Client " + clientNo + "'s host address is " + inetAddress.getHostAddress() + '\n');
				
				clientNo++;
			}
		}
		catch(IOException exception){
			System.err.println(exception);
		}
	} 
	/*class HandleAClient implements Runnable {
       private Socket socket;
       
       public HandleAClient(Socket socket) {
    	   this.socket = socket;
       }
       
       public void run() {
    	   try{
    		   DataInputStream inputStream = new DataInputStream(socket.getInputStream());
    		   DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
    		   
    		   while(true){
    			double radius = inputStream.readDouble();
    			
   				double area = radius * radius * Math.PI;
	            
   				outputStream.writeDouble(area);    
   				
				jta.append("Radius received from client:" + radius + '\n');
				jta.append("Area found:" + area + '\n');   				
    		   }
    	   }
   		catch(IOException ex){
			System.err.println(ex);
		}		
	  }
	}*/

}
