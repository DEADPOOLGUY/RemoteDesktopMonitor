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
					System.out.println("sendIP连接成功");
					connect = !connect;
					break;
				}
			} catch (UnknownHostException e) {
				// TODO 自动生成的 catch 块
				System.out.println("地址错误");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				System.out.println("连接失败");
			}
		}
	}
}
