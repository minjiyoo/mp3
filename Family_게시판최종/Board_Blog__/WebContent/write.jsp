<%@ page language="java" contentType="text/html; charset=EUC-KR"

    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <title>�Խ���</title>
 <meta name="viewport" content="width=device-width, user-scalable=no">
 </head>
 <body>
 <script language = "javascript"> // �ڹ� ��ũ��Ʈ ����
 

 
function asdf(){
	window.alert(document.getElementById("fileN").value);
 }
function writeCheck()
  {
   var form = document.writeform;
   
   if( !form.name.value )   // form �� �ִ� name ���� ���� ��
   {
    alert( "�̸��� �����ּ���" ); // ���â ���
    form.name.focus();   // form �� �ִ� name ��ġ�� �̵�
    return;
   }
   
   if( !form.password.value )
   {
    alert( "��й�ȣ�� �����ּ���" );
    form.password.focus();
    return;
   }
   
  if( !form.title.value )
   {
    alert( "������ �����ּ���" );
    form.title.focus();
    return;
   }
 
  if( !form.memo.value )
   {
    alert( "������ �����ּ���" );
    form.memo.focus();
    return;
   }
 
  form.submit();
  
  }


 </script>
<table width = "auto">

  <tr>
   <td>
    <table width=100% cellpadding="0" cellspacing="0" border="0">
     <tr style="background:url('img/table_mid.gif') repeat-x; text-align:center;">
      <td width="5"><img src="img/table_left.gif" width="5" height="30" /></td>
      <td>�۾���</td>
      <td width="5"><img src="img/table_right.gif" width="5" height="30" /></td>
     </tr>
    </table>
    <form name="writeform" method="post" action="write_ok.jsp">
   <table>
   <!-- <form method="post" enctype="multipart/form-data" action="imgup.jsp"> -->
	
	<tr>
      <td>&nbsp;</td>
      <td align="center">����</td>
      <td><input type="file" name="filename" id="fileN" size=40 onpropertyChange="setFileName(this.value)"></td>
      <!-- <td><input type="submit" value="���ε�"></td> -->
      <td>&nbsp;</td>
     </tr>
     <!-- </form> -->
    
     <tr>
      <td>&nbsp;</td>
      <td align="center" >����</td>
      <td><input name="title" size="50" width="100%" maxlength="100" ></td>
      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center">�̸�</td>
      <td><input name="name" id="N" size="50" maxlength="50"></td>
      <td>&nbsp;</td>
     </tr>
      <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center">��й�ȣ</td>
      <td><input name="password" size="50" maxlength="50"></td>
      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
     <tr>
      <td>&nbsp;</td>
      <td align="center">����</td>
      <td><textarea name="memo" cols="50" rows="13"></textarea></td>
      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
     <tr height="1" bgcolor="#82B5DF"><td colspan="4"></td></tr>
     <tr align="center">
      <td>&nbsp;</td>
      <td colspan="2">
      <!-- <input type=button value="dd" onClick="asdf()"> -->
      <input type=button value="���" OnClick="javascript:writeCheck();"> 
      <input type=button value="���" OnClick="javascript:history.back(-1)">
      <td>&nbsp;</td>
     </tr>
    </table>
   </td>
  </tr>
  </form>
 </table>
 
<style type="text/css" media="all">
	@media all {
		body{
			width : "auto";
			background-color: rgb(140,196,223);

		}
	
	}
	
	
</style>
</body>

</html>
