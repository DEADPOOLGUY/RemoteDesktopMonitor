package Administrator;
import java.sql.* ;
public class DatabaseConnection {
	private static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver" ;
 	private static final String DBURL = "jdbc:sqlserver://localhost:1433;DatabaseName=�����" ;
	private static final String DBUSER = "sa" ;
	private static final String DBPASSWORD = "123456" ;
	private Connection conn = null ;
	public DatabaseConnection() throws Exception{
		try{
			Class.forName(DBDRIVER) ;
			this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD) ;
			System.out.println("���ݿ����ӳɹ�");
		}catch(Exception e){
			throw e ;
		}
	}
	public Connection getConnection(){
		return this.conn ;
	}
	public void close() throws Exception{
		if(this.conn != null){
			try{
				this.conn.close() ;
			}catch(Exception e){
				throw e ;
			}
		}
	}
}