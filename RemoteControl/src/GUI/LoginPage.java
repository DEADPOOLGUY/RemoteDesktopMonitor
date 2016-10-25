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
	private JLabel jlTitle = new JLabel("远程桌面监控系统登录",JLabel.CENTER);
	private JLabel jlName = new JLabel("用户ID：");
	private JLabel jlPassword = new JLabel("密    码：");
	private JTextField jtName = new JTextField(12);
	private JPasswordField jtPassword = new JPasswordField(12);
	private NewButton jbOK = new NewButton("确定",new ImageIcon("image/4.png"));
	private NewButton jbReset = new NewButton("重置",new ImageIcon("image/7.png"));
	
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
					JOptionPane.showMessageDialog(null, "用户id不能为空！", "提示", JOptionPane.DEFAULT_OPTION);
				}
				else if(userpass==null || "".equals(userpass)){
					JOptionPane.showMessageDialog(null, "密码不能为空！", "提示", JOptionPane.DEFAULT_OPTION);
				}
				else {	// 里面没有记录任何的错误
					User user = new User() ;
					user.setUserid(userid) ;
					user.setPassword(userpass);
					try{
						if(DAOFactory.getIUserDAOInstance().findLogin(user)){
							JOptionPane.showMessageDialog(null, "用户 " + user.getName() + " 登录成功!", "提示", JOptionPane.DEFAULT_OPTION);
							setVisible(false);
							new Server();
							
						} else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误！", "提示", JOptionPane.DEFAULT_OPTION);
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
        frame1.setTitle("登录界面");
        frame1.setSize(400,300);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        JFrame frame1 = new LoginPage();
        frame1.setTitle("登录界面");
        frame1.setSize(400,300);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
	}

}
