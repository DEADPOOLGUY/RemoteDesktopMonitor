package ConnectServer;

import java.io.IOException;
import java.net.*;

public class ConnectServer {
	Socket cnnServer;
	String serverAddress;
	int port; 
	public ConnectServer(String serverAddress, int port){
		this.serverAddress = serverAddress;         //��������ַ
		this.port = port;         //�˿�
		try {
			cnnServer = new Socket(serverAddress, port);       //����
			System.out.println("���ӳɹ�");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("�˿ڴ������IP��ַ����");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����ʧ��");
		}
	}
	public boolean isConnected(){
		if(cnnServer == null) return false;
		else return true;
	}
	public Socket getSocket(){
		return this.cnnServer;
	}
}
