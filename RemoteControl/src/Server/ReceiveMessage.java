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
    	System.out.println("��Ϣ���շ���������");
    }
    
	public void receive(){//��Ϣ���չ���
		while (true) {
			Socket socket = null;
			try{
				socket = serverSocket.accept();
				
				Thread thread = new Thread(new RecMessHandler(socket,jta));
				fixedThreadPool.execute(thread);
				System.out.println("��Ϣ�������ӳɹ�");
				//thread.start();

			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
	}
       class RecMessHandler implements Runnable{//��Ϣ�����߳�
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
  		      ip = ip.substring(index+1);//ȡ�øÿͻ���ip���λ
              ip = "�ͻ���" + ip + ":";
              String msg = "";
              int len;
              byte[] buf = new byte[1024*8]; 
              while ((len=is.read(buf)) > 0)//�����յ���Ϣ���뻺���
               {
            	  System.out.println(len);
            	  msg  += new String(buf, 0, len); 
              }
              System.out.println(len);
              jta.append(ip + msg +"\n");//����Ϣ�������Ϣ
            
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
        


