package GUI;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

public 	class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor {
	  //~ Static fields/initializers -------------------------------------------------------------------------------------

	  private static final long serialVersionUID = 1L;

	  //~ Instance fields ------------------------------------------------------------------------------------------------

	  protected JCheckBox checkBox;

	  //~ Constructors ---------------------------------------------------------------------------------------------------

	  public CheckBoxCellEditor() {
	    checkBox = new JCheckBox();
	    checkBox.setHorizontalAlignment(SwingConstants.CENTER);
	    // checkBox.setBackground( Color.white);
	  }

	  //~ Methods --------------------------------------------------------------------------------------------------------

	  public Object getCellEditorValue() {
	    return Boolean.valueOf(checkBox.isSelected());
	  }

	  //~ ----------------------------------------------------------------------------------------------------------------

	  public Component getTableCellEditorComponent(
	    JTable  table,
	    Object  value,
	    boolean isSelected,
	    int     row,
	    int     column) {
	    checkBox.setSelected(((Boolean) value).booleanValue());

	    return checkBox;

	  }
	} // end class CheckBoxCellEditor
