package Administrator ;
import Administrator.User ;
public interface IUserDAO {
	// ������ɵ��ǵ�½��֤����ô��½����ֻ�����ַ��ؽ��
	public boolean findLogin(User user) throws Exception ;
} 