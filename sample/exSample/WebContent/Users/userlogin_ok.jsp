<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/Include/topmenu.jsp" %>

<HTML>


<TITLE>본 사이트에 오신 것을 환영합니다..</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt; }
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
-->

</STYLE>
<!-- <script>
	function send() {
		logout.action="user_logout";
		logout.submit();
	}
</script> -->
<table border="0" width="800">
<tr>
  <td width="20%"  bgcolor="#ecf1ef" valign="top" style="padding-left:0;">
	
	<!--로그인 영역 삽입-->	<%@ include file="/Include/login_form.jsp" %>
	

  </td>
  <td width="80%" valign="top" >&nbsp;<h4></h4><br> 

<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330 style="margin-left: 200px">
	
  <TR BGCOLOR=#7AAAD5>
    <td align=left><img src=./Member/img/h_b02.gif></td>
    <td align=center><FONT COLOR="#FFFFFF"><b>로그인 성공</b></FONT></td>
    <td align=right><img src=./Member/img/f_b03.gif></td>
  </tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330 style="margin-left: 200px">
<TR BGCOLOR=#948DCF>
  <TD>
    <TABLE CELLPADDING=4 CELLSPACING=1 BORDER=0 WIDTH=330>
  	  <TR BGCOLOR="#FFFFFF">
        <TD ALIGN="center">
          ${user.name} /${user.userid }님 환영합니다.<br><br>
        </TD>
      </TR>
    </TABLE>
 </TD>
</TR>
</TABLE>

<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330 style="margin-left: 200px">
  <TR BGCOLOR=#7AAAD5>
    <td align=left><img src=./Member/img/h_b04.gif></td>
    <td align=right><img src=./Member/img/h_b05.gif></td>
  </tr>
</table>

<br><br>
<form name="logout" method="post">
<table cellpadding=0 cellspacing=0 border=0 width=330 style="margin-left: 200px">
  <tr>
    <td align="center">
      <a href="user_modify">[자기정보수정]</a>
      <a href="user_logout">[로그아웃]

	</td>
  </tr>
  </td>
</table>
</table>
</form>
</BODY>
</HTML>