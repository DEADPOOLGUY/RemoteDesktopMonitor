package Server;

import javax.swing.JButton;

public class Server{
	static int MESSAGEPORT = 8889;
	static int FILEPORT = 8888;
	
	public Server(){
		
	}
	
	public void recImg(JButton[] jbtAry) {
		ReciveImg ri = new ReciveImg();
		ri.getImg(jbtAry);
	}
	public void sendMessage(String string) {
       new SendMessage(string,"localhost",MESSAGEPORT);
	}
	
	public void sendFlie(){
       new SendFile("localhost",FILEPORT);
	}

}
