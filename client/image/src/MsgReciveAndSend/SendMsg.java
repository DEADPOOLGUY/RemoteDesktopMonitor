package MsgReciveAndSend;
/*
 * ������Ϣ����
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SendMsg{
	public SendMsg(){
		final Socket s;
		//������Ϣ�Ĵ���
		final JFrame msgForm;
		try {
			s = new Socket("172.22.11.116",8002);
			//s.setSoTimeout(30000);
			msgForm = new JFrame("������Ϣ");
			//��������֮ǰΪ���ɼ�
			msgForm.setVisible(true);
			msgForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			msgForm.setLayout(null);
			Toolkit t = Toolkit.getDefaultToolkit();
			Dimension d = t.getScreenSize();
			//���ô���Ĵ�С��Ϊ��Ļ��3/8
			int scrwidth = (int) d.getWidth();
			int scrheight = (int) d.getHeight();
			msgForm.setBounds(scrwidth*3/8, scrheight*3/8, scrwidth/4, scrheight/4);
			msgForm.setResizable(false);
			//
			JPanel jp = new JPanel();
			jp.setBounds(0, msgForm.getHeight()*2/3, msgForm.getWidth(), msgForm.getHeight()/3);
			jp.setLayout(null);
			//���ܰ�ť
			JButton confirm = new JButton();
			confirm.setText("ȷ�Ϸ���");
			confirm.setBounds(jp.getWidth()/7, jp.getHeight()/6, jp.getWidth()*1/4, jp.getHeight()*3/10);
			JButton clear = new JButton("���");
			clear.setBounds(jp.getWidth()*15/28, jp.getHeight()/6, jp.getWidth()*1/4, jp.getHeight()*3/10);
			
			jp.add(confirm);
			jp.add(clear);
			//�����
			final JTextArea msgTxtArea = new JTextArea();
			msgTxtArea.setBounds(0, 0, msgForm.getWidth(), msgForm.getHeight()*2/3);
			msgTxtArea.setLineWrap(true);
			JScrollPane jsp = new JScrollPane(msgTxtArea);
			jsp.setBounds(0, 0, msgForm.getWidth(), msgForm.getHeight()*2/3);
			jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			msgForm.add(jsp);
			msgForm.add(jp);
			//Ϊȷ�ϰ�ť����¼�
			confirm.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					String msg = msgTxtArea.getText();
					OutputStream os = null;
					if(msg.equals(null)){
						JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ��", "������Ϣ", JOptionPane.DEFAULT_OPTION);
					}
					else{
						try {
							os = s.getOutputStream();
							os.write(msg.getBytes());
							os.flush();
							msgTxtArea.setText("");
							msgForm.setVisible(false);
							JOptionPane.showMessageDialog(null, "���ͳɹ�", "����", JOptionPane.DEFAULT_OPTION);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "��ȡ�׽����������", "������Ϣ", JOptionPane.DEFAULT_OPTION);
						}
					}
				}
				
			});
			//Ϊ��հ�ť����¼�
			clear.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					msgTxtArea.setText("");
				}
				
			});
		} catch (SocketException e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "���ӷ�������ʱ", "������Ϣ", JOptionPane.DEFAULT_OPTION);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
