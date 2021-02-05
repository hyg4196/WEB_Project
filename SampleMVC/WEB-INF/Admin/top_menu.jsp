<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>관리자</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
function ready() {
	alert("해당 페이지는 현재 준비중입니다.");
}
</script>
<style type="text/css"> 
<!-- 
body,td,tr,table{font-size:9pt; font-family:tahoma;color:#000000;line-height:160%;} 

A:link {font-family:tahoma;font-size:9pt;color:#000000;text-decoration:none;} 
A:visited {font-family:tahoma;font-size:9pt;color:#000000;text-decoration:none;} 
A:active {font-family:tahoma;font-size:9pt;color:#000000;text-decoration:none;} 
A:hover {font-family:tahoma;font-size:9pt;color:#000000;text-decoration:underline;} 
--> 
</style> 
</HEAD>
<BODY topmargin=0 leftmargin="0" marginwidth="0" marginheight="0">
  <TABLE WIDTH=100% height=30 border=0 bgcolor="#FFCC66">
    <TR> 
			<TD width="20"></TD>
			<td width="100"> 
        <p><a href="Admin?cmd=notice_list">[공지사항 관리]</a></p>
      </td>
			<TD width="100"> 
        <P><a href="Admin?cmd=admin_boardList">[게시판 관리]</a></P>
      </TD>
			<TD width="100"> 
        <P><a href="javascript:ready()">[회원 관리]</a></P>
      </TD>
			<TD width="100"> 
        <P><a href="javascript:ready()">[자료실 관리]</a></P>
      </TD>
			<TD width="100"> 
        <P><a href="javascript:ready()">[방명록 관리]</a></P>
      </TD>
			<TD width="100"> 
        	<P><a href="javascript:ready()">[관리자 관리]</a></P>
      </TD>
      <TD width="100"> 
        <P><a href="User?cmd=user_logout">로그아웃</a></P>
      </TD>
    </TR>
  </TABLE>
</body>
</html>
