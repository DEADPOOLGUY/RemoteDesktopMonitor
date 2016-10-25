package Server;

import java.net.InetAddress;
import javax.swing.JTextArea;

public class MutliJtaThread implements Runnable{
	public JTextArea jta = new JTextArea();
	InetAddress inetAddress;
	int clientNo;
	
	public MutliJtaThread(JTextArea jta,int clientNo,InetAddress inetAddress){
		this.jta = jta;
		this.inetAddress = inetAddress;
		this.clientNo = clientNo;
	}
	public void run(){
		jta.append("Client " + clientNo + "'s host name is " + inetAddress.getHostName() + '\n');
		jta.append("Client " + clientNo + "'s host address is " + inetAddress.getHostAddress() + '\n');
		
	}
}
