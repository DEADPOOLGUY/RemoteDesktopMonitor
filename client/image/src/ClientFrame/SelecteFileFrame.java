package ClientFrame;
/*
 * 选择文件窗口
 */
import java.io.File;
import javax.swing.JFileChooser;
public class SelecteFileFrame {
	//文件路径
    File path;
	public SelecteFileFrame(){
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("请选择文件");
		//设置是否可以多选
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int yes = jfc.showOpenDialog(null);
		if(yes == jfc.APPROVE_OPTION){
			path = jfc.getSelectedFile();
		}
	}
	//返回所选文件的路径
	public File getPath(){
		return this.path;
	}
}
