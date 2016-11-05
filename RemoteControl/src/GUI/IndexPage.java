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
	private int[] values={};//�û��б�ѡ������
	private static Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel p1 = new JPanel();//
	private JPanel p2 = new JPanel();//��Ļ��ؿ����
    private JPanel p3 = new JPanel();//�ұ߿�����
    private JPanel p4 = new JPanel();//��Ϣ�����
    private JPanel p6 = new JPanel();//�û��б�����
    private Button b1 = new Button("����");//��Ϣ���Ͱ�ť
    private Button b2 = new Button("������ȫ��");//��Ϣ������ȫ�尴ť
    private Button b3 = new Button("ȷ��ѡ��");//�û��б��ȷ��ѡ��ť
    private Button b4 = new Button("ȫѡ");//�û��б��ȫѡ��ť  
    private Button b5 = new Button("���ѡ��");//�û��б��ȫѡ��ť    
	private NewButton btn1 = new NewButton("Զ�̹ػ�����",new ImageIcon("image/mouse.png")) ;
	private NewButton btn2 = new NewButton("�˳�",new ImageIcon("image/off.png")) ;
	private NewButton btn3 = new NewButton("�����ļ�",new ImageIcon("image/flie.png")) ;
	private NewButton btn4 = new NewButton("��Ļ���",new ImageIcon("image/Desktop.png")) ;
	private NewButton btn5 = new NewButton("����",new ImageIcon("image/help.png")) ;	
	private NewButton btn6 = new NewButton("��Ļ�㲥",new ImageIcon("image/broadcast.png")) ;	
	private NewButton btn7 = new NewButton("Զ��˯�߿���",new ImageIcon("image/set.png")) ;	
    public JTextArea jta1 = new JTextArea();//�û���Ϣ�ı���
    public JTextArea jta2 = new JTextArea();//��Ϣ��д�ı���
    public JTable demoTable= new JTable(); //
    public CheckBoxCellEditor cbce = new CheckBoxCellEditor();
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> list = new JList<>(model);//
	public Server server;
	public NewButton[] jbtArray = new NewButton[60];//��Ļ�����ʾ��ť

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

        setTitle("Զ��������ϵͳ");
        setSize((int)screenSize.getWidth(),(int)(17*screenSize.getHeight()/18));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
	
	public void setDemoTable(){
			DefaultTableModel dtmDemo = (DefaultTableModel) demoTable.getModel();
			String[] tableHeads = { "�û�","��ѡ"};
			dtmDemo.setColumnIdentifiers(tableHeads);
			//�����1ָ���ǵڼ���,��0��ʼ����
	
			demoTable.getColumnModel().getColumn(1).setCellEditor(cbce);
			demoTable.getColumnModel().getColumn(1).setCellRenderer(new CWCheckBoxRenderer());
			//JTable�е�����
			for(int i=0;i<60;i++){
			 Object[] objdata = { "�ͻ���" + (i+1),new Boolean(false)/**������Ǹ�JCheckBoxλ��*/};
			 
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
    	tj.setBorder(BorderFactory.createTitledBorder("��Ļ���"));
        //tj.setBorder(BorderFactory.createRaisedBevelBorder());
    	p2.setLayout(new GridLayout(15,4));
        p2.setBorder(BorderFactory.createRaisedBevelBorder());
		int i = 0; 
		for(i = 0; i < jbtArray.length; i++){
			jbtArray[i] = new NewButton("�ͻ���"+ (i+1),new ImageIcon("image/c0.png"));
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
        jsp1.setBorder(BorderFactory.createTitledBorder("�û���Ϣ"));

        
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
    		model.addElement("-------------------�ͻ���" + (i+1) + "-------------------");
    	}
    	
        jsp2.setViewportView(list);
        jsp2.setPreferredSize(new Dimension(300, 200));
        jsp2.setBorder(BorderFactory.createTitledBorder("�û��б�"));      
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
        jsp3.setBorder(BorderFactory.createTitledBorder("������Ϣ"));
        
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
				   if(0 == JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ�˳�ϵͳ��","��ʾ",JOptionPane.YES_NO_OPTION)){
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
						   btn4.setToolTipText("�ر���Ļ���");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					  }finally {
						flag = 0;
					}
			   } else {
				    server.stopRecImg();
				    btn4.setToolTipText("������Ļ���");
                    flag = 1;
			     }

			}
			
		});
    	
        btn5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "�ڶԻ�������ʾ�������Ե�����", "����", JOptionPane.DEFAULT_OPTION);
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
					JOptionPane.showMessageDialog(null, "���ڷ�����Ϣ�����������֣�", "��ʾ", JOptionPane.DEFAULT_OPTION);
				//String input = JOptionPane.showInputDialog(null,"��������Ҫ���͵���Ϣ��","������Ϣ", JOptionPane.DEFAULT_OPTION);
				else {
					server.sendSpecifiedMessage(input);
					jta1.append("�ң�" + input + "\n");
				}
			}
			
		});
        
    	b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = jta2.getText();
				if(input.getBytes().length == 0)
					JOptionPane.showMessageDialog(null, "���ڷ�����Ϣ�����������֣�", "��ʾ", JOptionPane.DEFAULT_OPTION);
				//String input = JOptionPane.showInputDialog(null,"��������Ҫ���͵���Ϣ��","������Ϣ", JOptionPane.DEFAULT_OPTION);
				else {
					server.sendMessage(input);
					jta1.append("�ң�" + input + "\n");
				}
			}
			
		});
    	
    	b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				if(list.isSelectionEmpty()){
					JOptionPane.showMessageDialog(null, "��ѡ��ͻ��ˣ�", "��ʾ", JOptionPane.DEFAULT_OPTION);
				}else{
					setValues(list.getSelectedIndices());
					String display = "";
					for(int i =0 ;i<getValues().length;i++){
						System.out.println(getValues()[i] + 1);
						display += String.valueOf(getValues()[i]+1) + " ";
						//display += (String)values[i]+" ";
					}
						JOptionPane.showMessageDialog(null, "��ѡ��ͻ��ˣ�"+ display + "��", "��ʾ", JOptionPane.DEFAULT_OPTION);
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
