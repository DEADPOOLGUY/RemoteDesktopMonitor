package GUI;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public 	class CWCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
	  //~ Static fields/initializers -------------------------------------------------------------------------------------

	  private static final long serialVersionUID = 1L;

	  //~ Instance fields ------------------------------------------------------------------------------------------------

	  Border border = new EmptyBorder(1, 2, 1, 2);

	  //~ Constructors ---------------------------------------------------------------------------------------------------

	  public CWCheckBoxRenderer() {
	    super();
	    setOpaque(true);
	    setHorizontalAlignment(SwingConstants.CENTER);
	  }

	  //~ Methods --------------------------------------------------------------------------------------------------------


	  public Component getTableCellRendererComponent(
	    JTable  table,
	    Object  value,
	    boolean isSelected,
	    boolean hasFocus,
	    int     row,
	    int     column) {
	    if (value instanceof Boolean) {
	      setSelected(((Boolean) value).booleanValue());

	      // setEnabled(table.isCellEditable(row, column));
	      setForeground(table.getForeground());
	      setBackground(table.getBackground());

	    }

	    return this;
	  }
	} // end class CWCheckBoxRenderer



