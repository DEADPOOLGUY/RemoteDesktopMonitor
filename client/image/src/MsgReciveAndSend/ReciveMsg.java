package MsgReciveAndSend;
/*
 * ������Ϣ��
 */
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
    public ReciveMsg(){
    	
    }
	//������Ϣ���߳� �ں�������
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		try {
			System.out.println("������Ϣ�߳̿�ʼ");
			ServerSocket ss = new ServerSocket(8003);      
			Socket s;
			//ѭ��������ʦ�˵�����
			while(true){
				s = ss.accept();
				System.out.println("���ܵ�����˵���Ϣ����");
				InputStream is = s.getInputStream();
				byte[] buf = new  byte[1024*8];
				//��װ��Ϣ���ַ���
				String msg = "";
				//��ȡ��Ϣ����
				for(int len=is.read(buf);len>0;len=is.read(buf)){ 
				//int len = is.read(buf);
				msg += new String(buf, 0, len);
	                }
				//�ж��Ƿ�Ϊ�ػ����ߺ�������
				if(msg.equals("sleep")){
					Runtime rt = Runtime.getRuntime();
					Process p = rt.exec("rundll32.exe powrProf.dll, SetSuspendState");
					JOptionPane.showConfirmDialog(null, "���ļ�����ܵ�Զ�̺������ƣ����������60s��˯�ߣ��뱣����ļ���", "��ʾ", JOptionPane.DEFAULT_OPTION);
				}else if(msg.equals("shutdown")){
					Runtime rt = Runtime.getRuntime();
					JOptionPane.showConfirmDialog(null, "���ļ�����ܵ�Զ�̺������ƣ����������60s��˯�ߣ��뱣����ļ���", "��ʾ", JOptionPane.DEFAULT_OPTION);
					Process p = rt.exec("shutdown -s -t 0");
				}
				else{
					JOptionPane.showMessageDialog(null, msg, "������ʦ����Ϣ", JOptionPane.DEFAULT_OPTION);
				}
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
