package Server;

public class Server{
	static int MESSAGEPORT = 8889;
	static int FILEPORT = 8888;
	
	public Server(){
		
	}
	
	public void sendMessage(String string) {
       new sendMessage(string,"localhost",MESSAGEPORT);
	}
	
	public void sendFlie(){
       new sendFile("localhost",FILEPORT);
	}

}
