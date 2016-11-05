package Server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SendMessage {
   private Socket socket;
   private OutputStream os = null;
   private ByteArrayOutputStream bout = null;
   
   public SendMessage(String string,HashMap<String,InetAddress> map,int port){
       
	   try{
		   for(String key:map.keySet()){//根据传入的存储用户ip信息的hashmap实现循环发送
		       socket = new Socket(map.get(key).getHostAddress(),port);//建立socket
			   os =  socket.getOutputStream();//实例化输出流
			   os.write(string.getBytes());//将消息字符串转换为字节
			   os.flush();     
		   }

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
