package sendIP;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ConnectServer.ConnectServer;

public class SendIP {
	public SendIP(){
		Socket s;
		boolean connect = false;
		while(!connect){
			try {
				while((s = new Socket("172.22.11.116",8005))!=null){
					System.out.println("sendIP���ӳɹ�");
					connect = !connect;
					break;
				}
			} catch (UnknownHostException e) {
				// TODO �Զ����ɵ� catch ��
				System.out.println("��ַ����");
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				System.out.println("����ʧ��");
			}
		}
	}
}
