package FileReciveAndSend;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ClientFrame.SelecteFileFrame;
import ConnectServer.ConnectServer;

public class SendFile {
	public SendFile(){
			Socket s;
			try {
				s = new Socket("172.22.11.116",8006);
				System.out.println("���ӷ������ļ��˿ڳɹ�");
				SelecteFileFrame sff = new SelecteFileFrame();
				File file = sff.getPath();
				int length;
			    DataOutputStream toServer = null;
			    FileInputStream fis = null;
			    if(file != null){
					OutputStream os;
					try {
						os = s.getOutputStream();
						toServer = new DataOutputStream(os);
				    	fis = new FileInputStream(file);
				    	byte[] sendByte = new byte[1024];
				    	toServer.writeUTF(file.getName());
				    	while((length = fis.read(sendByte, 0, sendByte.length))>0){
				    		toServer.write(sendByte, 0, length);
				    		toServer.flush();
				    	}//while
				    	JOptionPane.showMessageDialog(null, "�ļ����ͳɹ���", "��ʾ", JOptionPane.DEFAULT_OPTION);
					} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}//catch
			    }//if file
			}catch(SocketException e){
				JOptionPane.showMessageDialog(null, "���ӷ�������ʱ", "������Ϣ", JOptionPane.DEFAULT_OPTION);
			} catch (UnknownHostException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
	}
}
		
