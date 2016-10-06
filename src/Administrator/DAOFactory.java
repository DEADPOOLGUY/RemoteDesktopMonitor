package Administrator ;
public class DAOFactory {
	public static IUserDAO getIUserDAOInstance(){
		return new UserDAOProxy() ;
	}
}