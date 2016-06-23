import java.util.Date;
import java.text.* ;
public class test {
	public static void main(String[] args) {
		
		ConnectDatabase con = new ConnectDatabase("family","talk") ;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd"); 
	    String date = format.format(new Date()); 
		String msg = "ABC" ;
		String user = "name" ;
		con.Insert("insert into talk (text, user, date) values ('"+msg+"' , '"+user+"', '"+date+"' ) ;") ;
		//con.Insert("insert into talk (text, date) values ('"+msg+"' , '"+date1+"' ) ;") ;
	}
}
