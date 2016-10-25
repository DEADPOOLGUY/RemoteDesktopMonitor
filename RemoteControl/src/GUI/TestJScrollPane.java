package GUI;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TestJScrollPane extends JPanel {

	 private static final long serialVersionUID = 1L;

	 public TestJScrollPane() {
	  super();
	  this.setLayout(null);
	  this.setBounds(200, 200, 200, 300);

	  JLabel label = new JLabel("����ǳ��Java Swing �������");

	  JPanel panel = new JPanel();
	  panel.add(label);
	  
	  JScrollPane scrollPane = new JScrollPane(panel);
	  scrollPane.setBounds(100, 100, 100, 300);
	  /**
	   * Ҫ�ӹ�������Ҫ��panel�Ŀ�ߴ���scrollPane�Ŀ��..��ֻҪ���µ�..ֻҪ�ߴ��ھ�����..
	   */
	  panel.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, scrollPane.getHeight()*2));
	  this.add(scrollPane);
	  panel.revalidate(); //������������,�ҵĿ�߱���
	  this.setVisible(true);

	 }
}