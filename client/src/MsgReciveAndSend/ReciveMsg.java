package MsgReciveAndSend;

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
	//private JTextArea jta = new JTextArea();
    public ReciveMsg(){
    	
    }
	/*public ReciveMsg(){
		setLayout(new BorderLayout());
		add(new JScrollPane(jta),BorderLayout.CENTER);
		
		setTitle("Server");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		/*try{
			Socket socket = null;
			ServerSocket serverSocket = new ServerSocket(8889);
			jta.append("Server started at" + new Date() + '\n');
		
			
			while(true){
				  socket = serverSocket.accept();
				  System.out.println("���ӳɹ�");
	              InputStream is = (InputStream) socket.getInputStream();  
	                String msg = socket.getInetAddress().getHostAddress() + ": ";  
	                byte[] buf = new byte[1024*8]; 
	                for(int len=is.read(buf);len>0;len=is.read(buf)){ 
	                msg+=new String(buf, 0, len); 
	                } 
	                jta.append(msg);
			}
			
			
		}
		catch(IOException ex){
			System.err.println(ex);
		}*/
		
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		try {
			System.out.println("������Ϣ�߳̿�ʼ");
			ServerSocket ss = new ServerSocket(8003);
			Socket s;
			while(true){
				s = ss.accept();
				System.out.println("���ܵ�����˵���Ϣ����");
				InputStream is = s.getInputStream();
				byte[] buf = new  byte[1024*8];
				String msg = "";
				for(int len=is.read(buf);len>0;len=is.read(buf)){ 
				//int len = is.read(buf);
				msg += new String(buf, 0, len);
	            System.out.print(len);
	                }
				if(msg.equals("shutdown")){
					Runtime rt = Runtime.getRuntime();
					Process p = rt.exec("rundll32.exe powrProf.dll, SetSuspendState");
					JOptionPane.showMessageDialog(null, "���ļ�����ܵ�Զ�̹ػ����ƣ�����60s��ػ����뱣����ļ���", "��ʾ", JOptionPane.DEFAULT_OPTION);
				}else{
					JOptionPane.showMessageDialog(null, msg, "������ʦ����Ϣ", JOptionPane.DEFAULT_OPTION);
				}
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
