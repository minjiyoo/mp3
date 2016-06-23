import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Date;

public class ServerThread extends Thread{
	Socket client;
	BufferedReader buffer;
	BufferedWriter bufferWriter;
	Vector<ServerThread> connectList;
	ConnectDatabase conn ;

	public ServerThread(Vector<ServerThread> connectList, Socket socket) {

		conn = new ConnectDatabase("family","talk") ;

		this.connectList = connectList;
		this.client = socket;
		try {
			buffer = new BufferedReader(new InputStreamReader((client.getInputStream())));
			bufferWriter = new BufferedWriter(new OutputStreamWriter((client.getOutputStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			String msg = listen();
			send(msg);
		}
	}

	//메시지 청취
	public String listen(){
		String msg="";
		try {
			msg= buffer.readLine();
			if(msg != null){		
				System.out.println("msg:"+msg);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd"); 
				String date = format.format(new Date()); 
				String user = "null" ;

				conn.Insert("insert into talk (text, user, date) values ('"+msg+"' ,'"+user+"' , '"+date+"' ) ;");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}

	//메시지 전송
	public void send(String msg){
		try {
			for(int i=0;i<connectList.size();i++){
				ServerThread st = connectList.get(i);

				st.bufferWriter.write(msg+"\n");
				st.bufferWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}