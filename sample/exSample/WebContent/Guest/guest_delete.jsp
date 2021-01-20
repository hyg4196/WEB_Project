<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	int idx = Integer.parseInt((request.getParameter("idx")));
	String name = request.getParameter("name");
	String userid = request.getParameter("userid");

%>
<html>
<head><title>방명록 삭제</title>
 <link rel="stylesheet" type="text/css" href="/stylesheet.css">
 <script>
 	function send(){
 		if(guest.pass.value==""){
 			alert("비번 입력");
 			guest.pass.focus();
 			return;
 		}
 		guest.action ="guest_delete.do";
 		guest.submit();
 	}
 </script>
 
 </head>
  <c:set var="name01" value="<%=name %>" />
 <c:set var="userid01" value="<%=userid %>" />
<c:if test="${userid01 ne name01}">
		<script>	
			alert("본인이 작성한 글이 아니면 수정 할 수 없습니다.");
			window.close();
		</script>
</c:if>
 
 <body>
 <form name = "guest" method="post" >
 	<input type="hidden" name ="idx" value = "<%=idx %>">
 	<input type="hidden" name ="page" value = "${page }">
   <table border="0" cellpadding="0" cellspacing="0" width="300" align="center">
     <tr>
       <td height="50">
       <img src="./img/bullet-05.gif"><b><font size="3" color="red">잠깐 !!</font></b></td></tr>
     <tr>
       <td valign="middle" height="30">
       <font size="2" face="돋움">게시물은 작성하신 분만 삭제할 수 있습니다. <br>
       글작성시 입력한 비밀번호를 입력해 주세요...</font></td></tr>
     <tr>
       <td valign="middle" height="40">
       <font size="2" face="돋움">
       비밀번호 <input type="password" name="pass" size="8"></font>
       <input type="submit" value="삭제" onClick = "send()">
       <input type="button" value="닫기" onClick = "javascript:self.close()"></td></tr>
   </table>
   </form>
 </body>
 </html>