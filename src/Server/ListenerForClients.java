package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class ListenerForClients{
    private int port = 8886;
    private ServerSocket serverSocket;
    JTextArea jTextArea;
    
	public ListenerForClients(JTextArea jTextArea) throws IOException{
		this.jTextArea = jTextArea;
    	serverSocket = new ServerSocket(port);
    	System.out.println("客户端IP地址监听服务器启动");

    }
	
	public void listen(){
		while (true) {
			Socket socket = null;
			try{
				socket = serverSocket.accept();
				Thread work = new Thread(new Handler(socket,jTextArea));
				work.start();
				work.interrupt();
				
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
	}
	
	class Handler implements Runnable{
		private Socket socket;
		JTextArea jTextArea;
		private Handler(Socket socket,JTextArea jTextArea){
			this.socket = socket;
			this.jTextArea = jTextArea;
		}
		
		public void run() {
			// TODO Auto-generated method stub
			InetAddress inetAddress = socket.getInetAddress();
			jTextArea.append("客户端IP:" + inetAddress.getAddress() + "已登录！\n");
			 
			try {
					if (socket != null)
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			
		}
	}

}
