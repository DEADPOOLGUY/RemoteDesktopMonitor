package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Administrator.*;
import Server.Server;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JFrame {
	private JLabel jlTitle = new JLabel("Զ��������ϵͳ��¼",JLabel.CENTER);
	private JLabel jlName = new JLabel("�û�ID��");
	private JLabel jlPassword = new JLabel("��    �룺");
	private JTextField jtName = new JTextField(12);
	private JPasswordField jtPassword = new JPasswordField(12);
	private NewButton jbOK = new NewButton("ȷ��",new ImageIcon("image/4.png"));
	private NewButton jbReset = new NewButton("����",new ImageIcon("image/7.png"));
	
    public LoginPage(){
    	JPanel jp1 = new JPanel(new FlowLayout());
    	JPanel jp2 = new JPanel(new FlowLayout());
    	JPanel jp3 = new JPanel(new FlowLayout());
    	
    	this.setLayout(new GridLayout(4, 1));
    	setButton();
    	jp1.add(jlName);
    	jp1.add(jtName);
    	
    	jp2.add(jlPassword);
    	jp2.add(jtPassword);
    	
    	jp3.add(jbOK);
    	jp3.add(jbReset);
    	
    	jlTitle.setFont(new java.awt.Font("dialog", 1, 25));
    	jlTitle.setForeground(Color.BLUE);
    	add(jlTitle);
    	add(jp1);
    	add(jp2);
    	add(jp3);
    }

    public void setButton(){
    	jbOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//String userid = "14251104202";
				//String userpass = "14251104202";
				String userid = jtName.getText();
			    String userpass = String.valueOf(jtPassword.getPassword());
				if(userid==null || "".equals(userid)){
					JOptionPane.showMessageDialog(null, "�û�id����Ϊ�գ�", "��ʾ", JOptionPane.DEFAULT_OPTION);
				}
				else if(userpass==null || "".equals(userpass)){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "��ʾ", JOptionPane.DEFAULT_OPTION);
				}
				else {	// ����û�м�¼�κεĴ���
					User user = new User() ;
					user.setUserid(userid) ;
					user.setPassword(userpass);
					try{
						if(DAOFactory.getIUserDAOInstance().findLogin(user)){
							JOptionPane.showMessageDialog(null, "�û� " + user.getName() + " ��¼�ɹ�!", "��ʾ", JOptionPane.DEFAULT_OPTION);
							setVisible(false);
							new Server();
							
						} else {
							JOptionPane.showMessageDialog(null, "�û������������", "��ʾ", JOptionPane.DEFAULT_OPTION);
							jtName.setText("");
							jtPassword.setText("");
						}
					}catch(Exception ex){
						ex.printStackTrace() ;
					}
			    }
			}
	   });
    	
    
      jbReset.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			jtName.setText("");
			jtPassword.setText("");
		}
		
	  });
    }
	public static void createLoginPage() {
		// TODO Auto-generated method stub
        JFrame frame1 = new LoginPage();
        frame1.setTitle("��¼����");
        frame1.setSize(400,300);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        JFrame frame1 = new LoginPage();
        frame1.setTitle("��¼����");
        frame1.setSize(400,300);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
	}

}
