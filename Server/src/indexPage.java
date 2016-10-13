import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.html.ImageView;

public class indexPage extends JFrame{
	private static Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private NewButton btn1 = new NewButton("Զ�̿�����ػ�",new ImageIcon("image/mouse.png")) ;
	private NewButton btn2 = new NewButton("ע��",new ImageIcon("image/off.png")) ;
	private NewButton btn3 = new NewButton("�����ļ�",new ImageIcon("image/flie.png")) ;
	private NewButton btn4 = new NewButton("��ʾ",new ImageIcon("image/Desktop.png")) ;
	private NewButton btn5 = new NewButton("����",new ImageIcon("image/help.png")) ;	
	private NewButton btn6 = new NewButton("������Ϣ",new ImageIcon("image/message.png")) ;	
	private Server server = new Server();
	
    public indexPage(){
    	
    	JPanel p1 = new JPanel();
    	p1.setLayout(new GridLayout(1,6));
    	p1.add(btn1);
    	p1.add(btn3);
    	p1.add(btn6);
    	p1.add(btn4);
    	p1.add(btn2);
    	p1.add(btn5);
        this.setButton();


    	
    	JPanel p2 = new JPanel();
    	p2.setLayout(new GridLayout(1,4));
        p2.setBorder(BorderFactory.createRaisedBevelBorder());
    	
    	JPanel p3 = new JPanel();
    	p3.setLayout(new GridLayout(4,1));
    	p3.add(new JButton());
    	p3.add(new JButton());
    	p3.add(new JButton());
    	p3.add(new JButton());

    	add(p1,BorderLayout.NORTH);
    	add(p2, BorderLayout.CENTER);
    	add(p3, BorderLayout.EAST);

    }
    public void setButton(){
    	btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   try {
				 setVisible(false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			
		});
    	btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   try {
				server.sendFlie();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			
		});
        btn5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "�ڶԻ�������ʾ�������Ե�����", "����", JOptionPane.DEFAULT_OPTION);
			}
			
		});
    	btn6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog(null,"��������Ҫ���͵���Ϣ��","������Ϣ", JOptionPane.DEFAULT_OPTION);
				server.sendMessage(inputValue);
			}
			
		});
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        JFrame frame = new indexPage();
        frame.setSize(screenSize);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}
