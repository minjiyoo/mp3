import java.sql.*;
public class ConnectDatabase {
 
	private String DB_name ;
	private String TB_name ;
	private Connection con ;
	private Statement stmt ;
	public ConnectDatabase(String db, String tb){
		this.DB_name = db ;
		this.TB_name = tb ;
 
		this.con = makeConnection() ;
 
		try {
			this.stmt = con.createStatement() ;
 
		} catch (SQLException e) {
			System.out.println("Error : ConnectDatabase - " + e.getMessage()) ;
			e.printStackTrace();
		}
 
	}
 
	public Connection makeConnection(){
		
		String url = "jdbc:mysql://localhost/"+this.DB_name ;
		String id = "root" ;
		String password = "rltnrtk" ;
		con = null ;
 
		try{
			//Class.forName("org.gjt.mm.mysql.Driver").newInstance();

			Class.forName("com.mysql.jdbc.Driver") ;
			System.out.println("����̹� ���� ����");
			con = DriverManager.getConnection(url,id,password) ;
			System.out.println("�����ͺ��̽� ���� ����");
		} 
		catch(ClassNotFoundException e){
			System.out.println("����̹��� ã�� �� �����ϴ�.") ;
			System.out.println(e.getMessage());
		}
		catch(SQLException e){
			System.out.println("���ῡ �����Ͽ����ϴ�.");
		}
		
		return con ;
	}
 
	public void Insert(String str){
		try {
			int i = stmt.executeUpdate(str) ;
			
			if(i==1)
				System.out.println("���ڵ� �߰� ����");
			else
				System.out.println("���ڵ� �߰� ����");
			
		} catch (SQLException e) {
			System.out.println("Error : ConnectDatabase.Insert - " + e.getMessage()) ;
			e.printStackTrace();
		}
	}
	
 
}