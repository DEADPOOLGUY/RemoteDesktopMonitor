import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;


public class Server{
    
    private DataOutputStream toServer;
    private DataInputStream fromServer;
	
	public Server(){
		
     try{
		Socket socket = new Socket("localhost", 8888);
		System.out.println("local port:" + socket.getLocalPort());
		fromServer = new DataInputStream(socket.getInputStream());
		toServer = new DataOutputStream(socket.getOutputStream());
		
	    }
	 catch(IOException ex){
		ex.printStackTrace();
	    }
	}
	public void sendMessage(String string) {

	    try{
		   double radius = Double.parseDouble(string);
		
		   toServer.writeDouble(radius);
		   toServer.flush();
		
		   double area = fromServer.readDouble();
		
		   System.out.println("Radius is " + radius + "\n");
		   System.out.println("Area received from the server is " + area + "\n");
	   }
	   catch(IOException ex){
		System.err.println(ex);
	   }
	}
	
	public void sendFlie(){
		try{
		       JFileChooser fileChooser = new JFileChooser();
		       
		       if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
		    	java.io.File file = fileChooser.getSelectedFile();
		    	
		    	//Scanner input = new Scanner(file);
		    	FileOutputStream f = new FileOutputStream(file);
		    	CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
		    	ZipOutputStream zos = new ZipOutputStream(csum);
		    	
		    	
		       }
		}
		catch(Exception ex){
			System.err.println(ex);
		}
	
	}	

}
