package Broadcasting;
import java.io.IOException;
import java.net.*;

import javax.swing.*;

import java.awt.event.*;

public class BroadCasting {
	
	public BroadCasting(){
		try{
			ServerSocket ss = new ServerSocket(8884);
			Socket s;
			Thread t = null;
			BroadCastingThread bct = new BroadCastingThread();
			while((s = ss.accept()) != null){
				System.out.println("���ӿ�ʼ");
				int info = s.getInputStream().read();
				System.out.println(info);
				if(info == 1){
					bct.setBol(true);
					t = new Thread(bct);
					t.start();
				}else if(info == 0){
					bct.setBol(false);         //ʹ���治�ܼ���
					t.stop();                  //�˳��߳�
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
