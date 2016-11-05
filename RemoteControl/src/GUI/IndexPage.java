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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ImageView;

import Server.Server;
import GUI.CWCheckBoxRenderer;
import GUI.CheckBoxCellEditor;

public class IndexPage extends JFrame{
	private int flag = 1;
	private int[] values={};//用户列表选择数组
	private static Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel p1 = new JPanel();//
	private JPanel p2 = new JPanel();//屏幕监控框面板
    private JPanel p3 = new JPanel();//右边框大面板
    private JPanel p4 = new JPanel();//消息框面板
    private JPanel p6 = new JPanel();//用户列表框面板
    private Button b1 = new Button("发送");//消息框发送按钮
    private Button b2 = new Button("发送至全体");//消息框发送至全体按钮
    private Button b3 = new Button("确认选择");//用户列表框确认选择按钮
    private Button b4 = new Button("全选");//用户列表框全选按钮  
    private Button b5 = new Button("清除选择");//用户列表框全选按钮    
	private NewButton btn1 = new NewButton("远程关机控制",new ImageIcon("image/mouse.png")) ;
	private NewButton btn2 = new NewButton("退出",new ImageIcon("image/off.png")) ;
	private NewButton btn3 = new NewButton("发送文件",new ImageIcon("image/flie.png")) ;
	private NewButton btn4 = new NewButton("屏幕监控",new ImageIcon("image/Desktop.png")) ;
	private NewButton btn5 = new NewButton("帮助",new ImageIcon("image/help.png")) ;	
	private NewButton btn6 = new NewButton("屏幕广播",new ImageIcon("image/broadcast.png")) ;	
	private NewButton btn7 = new NewButton("远程睡眠控制",new ImageIcon("image/set.png")) ;	
    public JTextArea jta1 = new JTextArea();//用户信息文本框
    public JTextArea jta2 = new JTextArea();//消息填写文本框
    public JTable demoTable= new JTable(); //
    public CheckBoxCellEditor cbce = new CheckBoxCellEditor();
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> list = new JList<>(model);//
	public Server server;
	public NewButton[] jbtArray = new NewButton[60];//屏幕监控显示按钮

    JScrollPane jsp2 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	JScrollPane tj = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
   
	public IndexPage(Server server){
    	this.server = server;
        this.setButton();
        
        this.setCatchScreen();
        
        this.setSide();
        
        this.setButtonFunction();
    	add(p1,BorderLayout.NORTH);
    	add(tj, BorderLayout.CENTER);
    	add(p3, BorderLayout.EAST);
    	//add(p4, BorderLayout.EAST);

        setTitle("远程桌面监控系统");
        setSize((int)screenSize.getWidth(),(int)(17*screenSize.getHeight()/18));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
	
	public void setDemoTable(){
			DefaultTableModel dtmDemo = (DefaultTableModel) demoTable.getModel();
			String[] tableHeads = { "用户","勾选"};
			dtmDemo.setColumnIdentifiers(tableHeads);
			//这里的1指的是第几列,从0开始计数
	
			demoTable.getColumnModel().getColumn(1).setCellEditor(cbce);
			demoTable.getColumnModel().getColumn(1).setCellRenderer(new CWCheckBoxRenderer());
			//JTable中的数据
			for(int i=0;i<60;i++){
			 Object[] objdata = { "客户端" + (i+1),new Boolean(false)/**这里就那个JCheckBox位置*/};
			 
			 dtmDemo.addRow(objdata);
			 
			}
			
			 System.out.println(cbce.getCellEditorValue());
			
	}
	
    public void setButton(){
    	
    	p1.setLayout(new GridLayout(1,7));
    	p1.add(btn1);
    	p1.add(btn7);
    	p1.add(btn6);
    	p1.add(btn4);
    	p1.add(btn3);
    	p1.add(btn5);
    	p1.add(btn2);

    }
    
    public void setCatchScreen() {
    	tj.setBorder(BorderFactory.createTitledBorder("屏幕监控"));
        //tj.setBorder(BorderFactory.createRaisedBevelBorder());
    	p2.setLayout(new GridLayout(15,4));
        p2.setBorder(BorderFactory.createRaisedBevelBorder());
		int i = 0; 
		for(i = 0; i < jbtArray.length; i++){
			jbtArray[i] = new NewButton("客户端"+ (i+1),new ImageIcon("image/c0.png"));
			//jbtArray[i].setBorder(BorderFactory.createRaisedBevelBorder());
			p2.add(jbtArray[i]);
			
		}
		tj.setViewportView(p2);
		tj.setPreferredSize(new Dimension((int) (5*screenSize.getWidth()/6), (int) (2*screenSize.getHeight()/3)));
		//System.out.println((5*screenSize.getWidth()/6) + "+" + (2*screenSize.getHeight()/3));
		tj.revalidate();
		//System.out.println(tj.getWidth() + "+" + tj.getHeight());
		
	}
    
    public void setSide(){

    	setDemoTable();
        p3.setLayout(new GridLayout(3, 1));
               
        jta1.setBorder(BorderFactory.createRaisedBevelBorder());
        jta1.setEditable(false);
        //jta1.setBackground(Color.blue);
        //jta1.add
   
        JScrollPane jsp1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp1.setViewportView(jta1);
        jsp1.setPreferredSize(new Dimension(300, 200));
        jsp1.setBorder(BorderFactory.createTitledBorder("用户信息"));

        
        jsp1.setPreferredSize(new Dimension((int) (screenSize.getWidth()/6), (int) (screenSize.getHeight()/3)));
        jsp1.revalidate();

        setMessagePanel();  
        setUsersPanel();
        //add(jsp1, BorderLayout.CENTER);
        p3.add(jsp1);
        p3.add(p4);
        p3.add(p6);


    }
    
    public void setUsersPanel(){
    	p6.setLayout(new BorderLayout());    	
    	list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    	
    	for(int i = 0;i<60;i++){
    		model.addElement("-------------------客户端" + (i+1) + "-------------------");
    	}
    	
        jsp2.setViewportView(list);
        jsp2.setPreferredSize(new Dimension(300, 200));
        jsp2.setBorder(BorderFactory.createTitledBorder("用户列表"));      
        jsp2.setPreferredSize(new Dimension((int) (screenSize.getWidth()/6), (int) (screenSize.getHeight()/3)));    
        jsp2.revalidate();
        
        JPanel p5 = new JPanel(new GridLayout(1,3));
        p5.add(b3);
        p5.add(b4);
        p5.add(b5);
        p6.add(p5, BorderLayout.SOUTH);
        p6.add(jsp2, BorderLayout.CENTER);
        
    	
    }
    
    public void setMessagePanel(){
    	p4.setLayout(new BorderLayout());
    	
        jta2.setBorder(BorderFactory.createRaisedBevelBorder());
        jta2.setLineWrap(true);
        
        JScrollPane jsp3 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp3.setViewportView(jta2);
        jsp3.setPreferredSize(new Dimension(300, 200));
        jsp3.setBorder(BorderFactory.createTitledBorder("发送消息"));
        
        jsp3.setPreferredSize(new Dimension((int) (screenSize.getWidth()/6), (int) (screenSize.getHeight()/3)));    
        jsp3.revalidate();
        
        JPanel p5 = new JPanel(new GridLayout(1,2));
        
        //b1.setPreferredSize(new Dimension((int) (screenSize.getWidth()/12), (int) (screenSize.getHeight()/30)));
        //b2.setSize(new Dimension((int) (screenSize.getWidth()/12), (int) (screenSize.getHeight()/30)));
        
        p5.add(b1);
        p5.add(b2);
        
        p4.add(jsp3,BorderLayout.CENTER);
        p4.add(p5,BorderLayout.SOUTH);
    	
    }
    public void setButtonFunction(){
    	btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   try {
				    server.sendShutDown();
				   
			   } catch (Exception e1) {
				// TODO Auto-generated catch block
				   e1.printStackTrace();
			   }
			}
			
		});
    	btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   try {
				   if(0 == JOptionPane.showConfirmDialog(null, "是否要退出系统？","提示",JOptionPane.YES_NO_OPTION)){
				      System.exit(0);
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
			   if (flag == 1) {
					try {
						   server.recImg();
						   btn4.setToolTipText("关闭屏幕监控");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					  }finally {
						flag = 0;
					}
			   } else {
				    server.stopRecImg();
				    btn4.setToolTipText("开启屏幕监控");
                    flag = 1;
			     }

			}
			
		});
    	
        btn5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "在对话框内显示的描述性的文字", "帮助", JOptionPane.DEFAULT_OPTION);
			}
			
		});
        
        btn7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				server.sendSleep();
		    }
		});
        
    	b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = jta2.getText();
				if(input.getBytes().length == 0)
					JOptionPane.showMessageDialog(null, "请在发送消息框内输入文字！", "提示", JOptionPane.DEFAULT_OPTION);
				//String input = JOptionPane.showInputDialog(null,"请输入您要发送的消息：","发送消息", JOptionPane.DEFAULT_OPTION);
				else {
					server.sendSpecifiedMessage(input);
					jta1.append("我：" + input + "\n");
				}
			}
			
		});
        
    	b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = jta2.getText();
				if(input.getBytes().length == 0)
					JOptionPane.showMessageDialog(null, "请在发送消息框内输入文字！", "提示", JOptionPane.DEFAULT_OPTION);
				//String input = JOptionPane.showInputDialog(null,"请输入您要发送的消息：","发送消息", JOptionPane.DEFAULT_OPTION);
				else {
					server.sendMessage(input);
					jta1.append("我：" + input + "\n");
				}
			}
			
		});
    	
    	b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				if(list.isSelectionEmpty()){
					JOptionPane.showMessageDialog(null, "请选择客户端！", "提示", JOptionPane.DEFAULT_OPTION);
				}else{
					setValues(list.getSelectedIndices());
					String display = "";
					for(int i =0 ;i<getValues().length;i++){
						System.out.println(getValues()[i] + 1);
						display += String.valueOf(getValues()[i]+1) + " ";
						//display += (String)values[i]+" ";
					}
						JOptionPane.showMessageDialog(null, "已选择客户端："+ display + "！", "提示", JOptionPane.DEFAULT_OPTION);
					}
				}

			
		});
    	
       	b4.addActionListener(new ActionListener() {
			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				int indices[] = new int[60];
    				for(int index=0;index<60;index++){
    					indices[index] = index;
    				}
    				list.setSelectedIndices(indices);
    			}
    		});
       	
       	b5.addActionListener(new ActionListener() {
			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				list.clearSelection();
    			}
    		});
    }
    	
	public static void createIndexPage() {
        //JFrame frame = new IndexPage();

	}

	public int[] getValues() {
		return values;
	}

	public void setValues(int[] values) {
		this.values = values;
	}

}
