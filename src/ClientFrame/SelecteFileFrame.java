package ClientFrame;

import java.io.File;
import javax.swing.JFileChooser;
public class SelecteFileFrame {
    File path;
	public SelecteFileFrame(){
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("ÇëÑ¡ÔñÎÄ¼þ");
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int yes = jfc.showOpenDialog(null);
		if(yes == jfc.APPROVE_OPTION){
			path = jfc.getSelectedFile();
		}
	}
	public File getPath(){
		return this.path;
	}
}
