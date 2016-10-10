package Server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class sendMessage {
   private Socket socket;
   private OutputStream os = null;
   private ByteArrayOutputStream bout = null;
   
   public sendMessage(String string,String ip,int port){
       
	   try{
	       socket = new Socket(ip,port);
		   os =  socket.getOutputStream();
		   os.write(string.getBytes());
		   os.flush();   
	   }
	   
	   catch(IOException ex){
		System.err.println(ex);
	   }finally {
		   try {
			if(os != null)
			   os.close();
			if(bout != null)   
			   bout.close();
			if(socket != null)
			   socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
   }
}
