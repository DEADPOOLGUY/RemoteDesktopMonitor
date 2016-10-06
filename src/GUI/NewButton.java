package GUI;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class NewButton extends JButton {

    public NewButton(String text,Icon icon){
    	super(text,icon);
    	this.setBorderPainted(false);
    	this.setContentAreaFilled(false);
    	this.setHorizontalTextPosition(SwingConstants.CENTER);
    	this.setVerticalTextPosition(SwingConstants.BOTTOM);
    	this.setContentAreaFilled(false);
    }
}
