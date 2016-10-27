package Server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.rtf.RTFEditorKit;

public class ReceiveMessage {
	
    private int port = 8889;
    private ServerSocket serverSocket;
    JTextArea jta;
    
    public ReceiveMessage(JTextArea jta) throws IOException{
    	this.jta = jta;
    	serverSocket = new ServerSocket(port);
    	System.out.println("消息接收服务器启动");
    }
    
	public void receive(){
		while (true) {
			Socket socket = null;
			try{
				socket = serverSocket.accept();

				Thread work = new Thread(new RecMessHandler(socket,jta));
				work.start();

			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
	}
       class RecMessHandler implements Runnable{
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
              String ip = socket.getInetAddress().getHostAddress() + ": ";
              String msg = "";
              byte[] buf = new byte[1024*8]; 
              for(int len=is.read(buf);len>0;len=is.read(buf)){ 
                 msg+=new String(buf, 0, len); 
              }
              System.out.println(msg);
              if("shutdown".equals(msg)){
            	  Runtime rt = Runtime.getRuntime();
            	  Process process = rt.exec("shutdown -s -t 60");
            	  JOptionPane.showMessageDialog(null, "您的计算机受到远程关机控制，将在60秒之后关机，请保存好文件！", "提示", JOptionPane.DEFAULT_OPTION);	    	
              }else if("sleep".equals(msg)){
            	  		Runtime rt = Runtime.getRuntime();
            	  		Process process = rt.exec("rundll32.exe powrProf.dll,SetSuspendState");
              		}else {
              			jta.append(ip + msg + "\n");
              		}
            
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
        


