package ClientFrame;
/*
 * ѡ���ļ�����
 */
import java.io.File;
import javax.swing.JFileChooser;
public class SelecteFileFrame {
	//�ļ�·��
    File path;
	public SelecteFileFrame(){
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("��ѡ���ļ�");
		//�����Ƿ���Զ�ѡ
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int yes = jfc.showOpenDialog(null);
		if(yes == jfc.APPROVE_OPTION){
			path = jfc.getSelectedFile();
		}
	}
	//������ѡ�ļ���·��
	public File getPath(){
		return this.path;
	}
}
