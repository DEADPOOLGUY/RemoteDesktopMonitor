package FileReciveAndSend;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ReciveFile implements Runnable {
	
	public ReciveFile(){
		
	}
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		try {
			ServerSocket ss = new ServerSocket(8001);
			Socket s;
			while((s =ss.accept()) != null){
				System.out.println("�ļ����ն˿�������");
				byte[] inputByte = null;
		        int length = 0;
		        DataInputStream din = null;
		        FileOutputStream fout = null;
		        try {
		            din = new DataInputStream(s.getInputStream());
		            
		            fout = new FileOutputStream(new File("D:\\"+din.readUTF()));
		            inputByte = new byte[1024];
		            System.out.println("��ʼ��������...");
		            while (true) {
		                if (din != null) {
		                    length = din.read(inputByte, 0, inputByte.length);
		                }
		                if (length == -1) {
		                    break;
		                }
		                System.out.println(length);
		                fout.write(inputByte, 0, length);
		                fout.flush();
		            }
		            JOptionPane.showMessageDialog(null, "�ɹ������ļ����Ѵ����D�̣�", "��ʾ", JOptionPane.DEFAULT_OPTION);
		            System.out.println("��ɽ���");
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        } finally {
		            if (fout != null)
		                fout.close();
		            if (din != null)
		                din.close();
		            if (s != null)
		                s.close();
		        }
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

}
