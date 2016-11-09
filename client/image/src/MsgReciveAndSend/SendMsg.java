package MsgReciveAndSend;
/*
 * 发送消息的类
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
		//发送消息的窗体
		final JFrame msgForm;
		try {
			s = new Socket("172.22.11.116",8002);
			//s.setSoTimeout(30000);
			msgForm = new JFrame("发送消息");
			//在连接上之前为不可见
			msgForm.setVisible(true);
			msgForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			msgForm.setLayout(null);
			Toolkit t = Toolkit.getDefaultToolkit();
			Dimension d = t.getScreenSize();
			//设置窗体的大小，为屏幕的3/8
			int scrwidth = (int) d.getWidth();
			int scrheight = (int) d.getHeight();
			msgForm.setBounds(scrwidth*3/8, scrheight*3/8, scrwidth/4, scrheight/4);
			msgForm.setResizable(false);
			//
			JPanel jp = new JPanel();
			jp.setBounds(0, msgForm.getHeight()*2/3, msgForm.getWidth(), msgForm.getHeight()/3);
			jp.setLayout(null);
			//功能按钮
			JButton confirm = new JButton();
			confirm.setText("确认发送");
			confirm.setBounds(jp.getWidth()/7, jp.getHeight()/6, jp.getWidth()*1/4, jp.getHeight()*3/10);
			JButton clear = new JButton("清空");
			clear.setBounds(jp.getWidth()*15/28, jp.getHeight()/6, jp.getWidth()*1/4, jp.getHeight()*3/10);
			
			jp.add(confirm);
			jp.add(clear);
			//输入框
			final JTextArea msgTxtArea = new JTextArea();
			msgTxtArea.setBounds(0, 0, msgForm.getWidth(), msgForm.getHeight()*2/3);
			msgTxtArea.setLineWrap(true);
			JScrollPane jsp = new JScrollPane(msgTxtArea);
			jsp.setBounds(0, 0, msgForm.getWidth(), msgForm.getHeight()*2/3);
			jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			msgForm.add(jsp);
			msgForm.add(jp);
			//为确认按钮添加事件
			confirm.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					String msg = msgTxtArea.getText();
					OutputStream os = null;
					if(msg.equals(null)){
						JOptionPane.showMessageDialog(null, "消息不能为空", "错误消息", JOptionPane.DEFAULT_OPTION);
					}
					else{
						try {
							os = s.getOutputStream();
							os.write(msg.getBytes());
							os.flush();
							msgTxtArea.setText("");
							msgForm.setVisible(false);
							JOptionPane.showMessageDialog(null, "发送成功", "提醒", JOptionPane.DEFAULT_OPTION);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "获取套接字输出错误", "错误消息", JOptionPane.DEFAULT_OPTION);
						}
					}
				}
				
			});
			//为清空按钮添加事件
			clear.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					msgTxtArea.setText("");
				}
				
			});
		} catch (SocketException e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "连接服务器超时", "错误消息", JOptionPane.DEFAULT_OPTION);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
