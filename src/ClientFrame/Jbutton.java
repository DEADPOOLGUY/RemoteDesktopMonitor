package ClientFrame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

class Jbutton extends JButton{
	boolean hover;
	public static final Color BUTTON_COLOR1 = new Color(135, 206, 250);
	public static final Color BUTTON_COLOR2 = new Color(0, 191, 215);
	public static final Color BUTTON_FOREGROUND_COLOR = Color.WHITE;
	public Jbutton(String path, String actioncmd){
		//设置属性
		setIcon(new ImageIcon(path));
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setActionCommand(actioncmd);
		//鼠标进入与退出按钮仰视的变化
		addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
			     setForeground(BUTTON_FOREGROUND_COLOR);
			     hover = true;
	             repaint();
			 }
             @Override
	         public void mouseExited(MouseEvent e) {
                 setForeground(BUTTON_COLOR2);
                 hover = false;
                 repaint();
             }
		});
	}
	protected void paintComponent(Graphics g){
		 Graphics2D g2d = (Graphics2D) g.create();
		 //得到按钮的长宽高
		 int h = getHeight();
		 int w = getWidth();
		 float tran = 1F;
		 if (!hover) {
		     tran = 0.3F;
		 }
		 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 RenderingHints.VALUE_ANTIALIAS_ON);
		 GradientPaint p1;
		 GradientPaint p2;
		 if (getModel().isPressed()) {
		     p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,
		     new Color(100, 100, 100));
		     p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
		     new Color(255, 255, 255, 100));
		 } else {
		     p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,
		     new Color(0, 0, 0));
		     p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,
		     h - 3, new Color(0, 0, 0, 50));
		 }
		 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
		 tran));
		 RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,
		 h - 1, 20, 20);
		 Shape clip = g2d.getClip();
		 g2d.clip(r2d);
		 GradientPaint gp = new GradientPaint(0.0F, 0.0F, BUTTON_COLOR1, 0.0F,
		 h, BUTTON_COLOR2, true);
		 g2d.setPaint(gp);
		 g2d.fillRect(0, 0, w, h);
		 g2d.setClip(clip);
		 g2d.setPaint(p1);
		 g2d.drawRoundRect(0, 0, w - 1, h - 1, 20, 20);
		 g2d.setPaint(p2);
		 g2d.drawRoundRect(1, 1, w - 3, h - 3, 18, 18);
		 g2d.dispose();
		 super.paintComponent(g);
	}
 }