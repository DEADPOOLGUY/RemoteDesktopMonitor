package GUI;
import Server.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.text.html.ImageView;

import Server.Server;

public class IndexPage extends JFrame{
	private static Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
	private NewButton btn1 = new NewButton("远程开机与关机",new ImageIcon("image/mouse.png")) ;
	private NewButton btn2 = new NewButton("注销",new ImageIcon("image/off.png")) ;
	private NewButton btn3 = new NewButton("发送文件",new ImageIcon("image/flie.png")) ;
	private NewButton btn4 = new NewButton("监控",new ImageIcon("image/Desktop.png")) ;
	private NewButton btn5 = new NewButton("帮助",new ImageIcon("image/help.png")) ;	
	private NewButton btn6 = new NewButton("发送消息",new ImageIcon("image/message.png")) ;	
    public JTextArea jta1 = new JTextArea();
    public JTextArea jta2 = new JTextArea();
    JList jl = new JList();
	public Server server;
	public NewButton[] jbtArray = new NewButton[30];
	//public JButton[] jbtArray = new JButton[4];
	JScrollPane tj = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
   
	public IndexPage(Server server){
    	this.server = server;
        this.setButton();
        
        this.setCatchScreen();
        this.setButtonFunction();
        
        this.setSide();
       
    	add(p1,BorderLayout.NORTH);
    	add(tj, BorderLayout.CENTER);
    	add(p3, BorderLayout.EAST);
    	

        setTitle("远程桌面监控系统");
        setSize((int)screenSize.getWidth(),(int)(17*screenSize.getHeight()/18));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void setButton(){
    	p1.setLayout(new GridLayout(1,6));
    	p1.add(btn1);
    	p1.add(btn3);
    	p1.add(btn6);
    	p1.add(btn4);
    	p1.add(btn2);
    	p1.add(btn5);

    }
    
    public void setCatchScreen() {
    	//tj.setBorder(BorderFactory.createTitledBorder("屏幕监控"));
        //tj.setBorder(BorderFactory.createRaisedBevelBorder());
    	p2.setLayout(new GridLayout(10,3));
        p2.setBorder(BorderFactory.createRaisedBevelBorder());
		int i = 0; 
		for(i = 0; i < jbtArray.length; i++){
			jbtArray[i] = new NewButton("客户端"+ (i+1),new ImageIcon("image/c0.png"));
			jbtArray[i].setBorder(BorderFactory.createRaisedBevelBorder());
			p2.add(jbtArray[i]);
			
		}
		tj.setViewportView(p2);
		tj.setPreferredSize(new Dimension(400, 2800));
		tj.revalidate();
		
	}
    
    public void setSide(){
    	DefaultListModel<String> model = new DefaultListModel<>();
    	JList<String> list = new JList<>(model);
    	list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    	
    	for(int i = 0;i<30;i++){
    	model.addElement("客户端" + (i+1));}
    	
        p3.setLayout(new GridLayout(1, 1));
               
        jta1.setBorder(BorderFactory.createRaisedBevelBorder());
        jta2.setBorder(BorderFactory.createRaisedBevelBorder());
        jta1.setEditable(false);
        //jta1.add
        jta2.setEditable(false);
        
        JScrollPane jsp1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp1.setViewportView(jta1);
        jsp1.setPreferredSize(new Dimension(300, 200));
        
        JScrollPane jsp2 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp2.setViewportView(list);
        jsp2.setPreferredSize(new Dimension(300, 200));
        
        jsp1.setPreferredSize(new Dimension((int) (screenSize.getWidth()/5), (int) (screenSize.getHeight()/3)));
        jsp1.revalidate();
                
        jsp2.setPreferredSize(new Dimension((int) (screenSize.getWidth()/5), (int) (screenSize.getHeight()/3)));    
        jsp2.revalidate();
        //add(jsp1, BorderLayout.SOUTH);
        //p3.add(jsp1);
        p3.add(jsp2);
    }
    
    public void setButtonFunction(){
    	//int i;
    	//for( i=0;i<jbtArray.length;i++){
    	   /*jbtArray[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   try {
				server.sendFlie();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
    	
		    });*/
    	   //}
    	btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   try {
				   if(0 == JOptionPane.showConfirmDialog(null, "是否要注销帐号？","提示",JOptionPane.YES_NO_OPTION)){
				      setVisible(false); 
				      //LoginPage.createLoginPage();
				   }
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
    	
    	btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   try {
				   server.recImg();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			
		});
    	
        btn5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "在对话框内显示的描述性的文字", "帮助", JOptionPane.DEFAULT_OPTION);
			}
			
		});
    	btn6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,"请输入您要发送的消息：","发送消息", JOptionPane.DEFAULT_OPTION);
				server.sendMessage(input);
			}
			
		});
    }
    	
	public static void createIndexPage() {
        //JFrame frame = new IndexPage();

	}

}
