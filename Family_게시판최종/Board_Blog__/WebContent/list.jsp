<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <title>�Խ���</title>
 </head>
 <body>
 <%
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	String url = "jdbc:odbc:board2";
	String id = "";
	String pass = "";
	int total = 0;
	
	int count = 0;
	
	
	try {
		Connection conn = DriverManager.getConnection(url,id,pass); //db����
		Statement stmt = conn.createStatement(); //statement Ÿ���� ��ü����

		String sqlCount = "SELECT COUNT(*) FROM board"; //db���� �ڷᰳ���� ã�� sql��
		ResultSet rs = stmt.executeQuery(sqlCount); //db����
		
		if(rs.next()){
			total = rs.getInt(1);
		}
		rs.close();
		
		
		String sqlList = "SELECT NUM, USERNAME, TITLE, MEMO, TIME, HIT, INDENT, IMAGE from board order by REF DESC, STEP ASC";
		rs = stmt.executeQuery(sqlList); //db����
		
%>
 <!-- 
<table width="100%" cellpadding="0" cellspacing="0" border="0">
  <tr><td colspan="4" height="5"></td></tr>
  <tr align="center">
   <td><input type="image" src="img/write.png" width="40px" height="40px" value="�۾���" OnClick="window.location='write.jsp'"></td>
  </tr>
</table> -->

<%
	if(total==0) {
%>
	 		
	 	   <p>��ϵ� ���� �����ϴ�.</p>
	 	 
<%
	 	} else {
	 		
		while(rs.next()) {
			int idx = rs.getInt(1);
			String name = rs.getString(2);
			String title = rs.getString(3);
			String memo = rs.getString(4);
			String time = rs.getString(5);
			int hit = rs.getInt(6);
			int indent = rs.getInt(7);
			String filename = rs.getString(8);
%>



<% 	
	for(int j=0;j<indent;j++){
%>		<table border="1px" width="100%">
		<tr>
		<td class="board_content" width="30%">&nbsp;&nbsp;&nbsp;<%
	}
	if(indent!=0){ //����϶�
%>		<img src='img/reply_icon.png' width="12px" height="12px"/>
		<%=name %></td>
		<td width="60%"><%=memo %></td>
		<td width="10%"><input type=button value="����" OnClick="window.location='delete.jsp?idx=<%=idx%>'"></td>
		</tr>
		</table>
		
<%	
		continue; }
%> 	
	<table>
	<tr class="zero" >
	<td class="zero" >&nbsp;</td>
	</tr>
	</table>
	<table border="1px" width="100%" >
	<tr class="board_content">
	<td rowspan="2" width="25%" align="center" ><img src='img/profile.png' width="40px" height="40px"/></td>
	<td width="25%"><%=name %></td>
	<td align="center" colspan="2"><%=time %></td>
	</tr>
	<tr class="board_content">
	<td width="25%"><input type=button value="����" OnClick="window.location='modify.jsp?idx=<%=idx%>'"></td>
	<td width="25%"><input type=button value="���" OnClick="window.location='reply.jsp?idx=<%=idx%>'"></td>
	<td width="25%"><input type=button value="����" OnClick="window.location='delete.jsp?idx=<%=idx%>'"></td>
	</tr>
	
	
	
	
	
	<tr><td>
	<img src="filename"></img>
	</td></tr> 
	
	<tr>
	<td colspan="4" height="150px"><%=memo %></td>
	</tr>
	</table>
<% 
		}
	} 
	rs.close(); //rs��ü ��ȯ
	stmt.close();
	conn.close();
} catch(SQLException e) {
	out.println( e.toString() ); //������ ��� ���� ���
}
%>
<style type="text/css" media="all">

	@font-face {
		font-family: 'HoonWhitecatR';
		src: url(font/HoonWhitecatR.ttf)  format('truetype');
	}
	body{
		font-family:'HonnWhitecatR';
	}
	
	.zero {
	border : 0px;
	}
	.board_content{
	background-color: rgb(10,170,223);
	color: white;
	}
	table, tr, td {
	border-color: rgb(10,170,223);
	}
	
	table {
	size : 150px;
	}
	
</style>
</body> 
</html>