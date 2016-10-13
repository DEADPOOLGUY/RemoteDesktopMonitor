package ReciveMsg;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.omg.CORBA.portable.InputStream;

public class ReciveMsg extends JFrame{
	private JTextArea jta = new JTextArea();
    
	public ReciveMsg(){
		setLayout(new BorderLayout());
		add(new JScrollPane(jta),BorderLayout.CENTER);
		
		setTitle("Server");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*try{
			Socket socket = null;
			ServerSocket serverSocket = new ServerSocket(8889);
			jta.append("Server started at" + new Date() + '\n');
		
			
			while(true){
				  socket = serverSocket.accept();
				  System.out.println("连接成功");
	              InputStream is = (InputStream) socket.getInputStream();  
	                String msg = socket.getInetAddress().getHostAddress() + ": ";  
	                byte[] buf = new byte[1024*8]; 
	                for(int len=is.read(buf);len>0;len=is.read(buf)){ 
	                msg+=new String(buf, 0, len); 
	                } 
	                jta.append(msg);
			}
			
			
		}
		catch(IOException ex){
			System.err.println(ex);
		}*/
		
	}
	public void setMsg(String msg){
		jta.append(msg);
	}
}
