package MsgReciveAndSend;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.io.InputStream;

public class ReciveMsg implements Runnable{
	//private JTextArea jta = new JTextArea();
    public ReciveMsg(){
    	Thread t = new Thread(this);
    	t.start();
    }
	/*public ReciveMsg(){
		setLayout(new BorderLayout());
		add(new JScrollPane(jta),BorderLayout.CENTER);
		
		setTitle("Server");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
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
		
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			ServerSocket ss = new ServerSocket(8889);
			Socket s;
			while((s = ss.accept())!= null){
				InputStream is = s.getInputStream();
				byte[] buf = new  byte[1024*8];
				String msg = "老师 ：";
				for(int len=is.read(buf);len>0;len=is.read(buf)){ 
	                msg+=new String(buf, 0, len); 
	                } 
				JOptionPane.showMessageDialog(null, msg, "来自老师的消息", JOptionPane.DEFAULT_OPTION);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
