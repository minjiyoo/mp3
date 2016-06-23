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
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url,id,password) ;
			System.out.println("데이터베이스 연결 성공");
		} 
		catch(ClassNotFoundException e){
			System.out.println("드라이버를 찾을 수 없습니다.") ;
			System.out.println(e.getMessage());
		}
		catch(SQLException e){
			System.out.println("연결에 실패하였습니다.");
		}
		
		return con ;
	}
 
	public void Insert(String str){
		try {
			int i = stmt.executeUpdate(str) ;
			
			if(i==1)
				System.out.println("레코드 추가 성공");
			else
				System.out.println("레코드 추가 실패");
			
		} catch (SQLException e) {
			System.out.println("Error : ConnectDatabase.Insert - " + e.getMessage()) ;
			e.printStackTrace();
		}
	}
	
 
}