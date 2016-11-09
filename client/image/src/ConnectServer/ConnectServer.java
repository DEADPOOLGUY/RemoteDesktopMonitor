package ConnectServer;
/*
 * 自定义的连接教师端类，通过套接字实现
 */
import java.io.IOException;
import java.net.*;

public class ConnectServer {
	Socket cnnServer;
	String serverAddress;
	int port; 
	public ConnectServer(String serverAddress, int port){
		this.serverAddress = serverAddress;         //服务器地址
		this.port = port;         //端口
		try {
			cnnServer = new Socket(serverAddress, port);       //连接
			System.out.println("连接成功");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("端口错误或者IP地址错误");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("连接失败");
		}
	}
	public boolean isConnected(){
		if(cnnServer == null) return false;
		else return true;
	}
	//返回套接字
	public Socket getSocket(){
		return this.cnnServer;
	}
}
