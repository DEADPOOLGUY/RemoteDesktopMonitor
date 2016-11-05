package Server;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.rtf.RTFEditorKit;

public class ReceiveMessage {
	
    private final int port = 8879;
    private ServerSocket serverSocket;
    JTextArea jta;
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(6);
    
    public ReceiveMessage(JTextArea jta) throws IOException{
    	this.jta = jta;
    	serverSocket = new ServerSocket(port);
    	System.out.println("消息接收服务器启动");
    }
    
	public void receive(){//消息接收功能
		while (true) {
			Socket socket = null;
			try{
				socket = serverSocket.accept();
				
				Thread thread = new Thread(new RecMessHandler(socket,jta));
				fixedThreadPool.execute(thread);
				System.out.println("消息接收连接成功");
				//thread.start();

			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
	}
       class RecMessHandler implements Runnable{//消息接收线程
         private Socket socket;
         JTextArea jta;
         
         public RecMessHandler(Socket socket,JTextArea jta) {
            this.socket = socket;
            this.jta = jta;
	         
        }
         
        public void run(){
        	
        	InputStream is = null;
        	try{
        		
        	  is = socket.getInputStream(); 
  			  InetAddress inetAddress = socket.getInetAddress();
  			  String ip = inetAddress.getHostAddress();
  			  int index = ip.lastIndexOf('.');
  		      ip = ip.substring(index+1);//取得该客户端ip最后位
              ip = "客户端" + ip + ":";
              String msg = "";
              int len;
              byte[] buf = new byte[1024*8]; 
              while ((len=is.read(buf)) > 0)//将接收到消息放入缓冲池
               {
            	  System.out.println(len);
            	  msg  += new String(buf, 0, len); 
              }
              System.out.println(len);
              jta.append(ip + msg +"\n");//在消息框添加消息
            
           }catch(IOException e){
        	   e.printStackTrace();
           }finally {
	           try{ 
        	    if (is != null)
	                is.close();
	            if (socket != null)
	                socket.close();
	            
		    }catch(IOException e){
		    	e.printStackTrace();
		    }
        }
    }
   }  
}
        


