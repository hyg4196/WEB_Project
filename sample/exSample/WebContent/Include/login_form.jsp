<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
 <title>Login</title>
<style>
	.fontcolor{
		color: black;
	}
</style>
<script>
	function send04(){
		login_form.submit();
	}
</script>
 </head>

 <body>
 
 
 <c:if test="${empty user}">
   <table width="100%" height="120" border="0">
   <form name="login_form" action="user_login" method="post" >
     <tr>
       <td colspan="2" bgcolor="#6FA0E" height="20" align="center">
         <font size="2" color="white"><b>Member Login</b></font>
       </td>
     </tr>
     <tr>
       <td ><font size="2">아 이 디</font></td>
       <td ><input type="text" size="10" name="userid"></td>
     </tr>
     <tr>
       <td><font size="2">비밀번호</font></td>
			 <td>
         <input type="password" size="10" name="passwd">
       </td>
     </tr>
     <tr>
       <td><input type="image" src="Include/img/login1.gif" border="0" onClick="send04()"></td>
			 <td>
           <a href="user_insert"><img src="Include/img/regist.gif" border="0"></a>
       </td>
     </tr>
 </form>
 </table>
 </c:if>
 
 <c:if test="${!empty user}">
 
 <table width="100%" height="120" border="0">
   <form name="login_form" action="user_login" method="post" >
     <tr>
       <td colspan="2" bgcolor="#6FA0E" height="20" align="center">
         <font size="2" color="white"><b>Member Login</b></font>
       </td>
     </tr>
     <tr>
     <td colspan="2">
     	 <font size="2" style="text-align: center;"><b>환영합니다. ${user.userid }님</b></font>			
     </td>
     </tr>
     <tr>
     <td>
		<a href="user_modify"><input type="button" value="정보수정"></a>	
		</td>		 
		<td>
           <a href="user_logout"><input type="button" value="로그오프"></a>
       </td>
     </tr>
 </form>
 </table>
 </c:if>

 <table width="100%" height="120" border="0">
   <tr>
     <td bgcolor="#6FA0E" align="center" height="20">
      <c:if test="${empty user}">
     	<font size="2" color="white">로그인 해주세요!</font>
     </c:if>
     <c:if test="${!empty user}">
     	<font size="2" color="white">${user.userid} 님!</font>
     </c:if>
     </td>
   </tr>
   <tr>
     <td align="center">
       <font size="2">
       <h3> 홈페이지 방문을<br> 환영합니다 !</h3>
       </font>
     </td>
   </tr>
 </table>
 <table width="100%" height="120" border="0">
   <tr>
     <td bgcolor="#6FA0E" align="center" height="20">
        <c:if test="${empty user}">
     	<font size="2" color="white">로그인 해주세요!</font>
     	</c:if>
	     <c:if test="${!empty user}">
	     	<font size="2" color="white">${user.name}님의<br>관리 메뉴 입니다.</font>
	     </c:if>
     </td>
   </tr>
   <tr>
     <td align="center">
       <font size="2">
       <c:if test="${empty user}">
     		<h5> 게시글은 로그인을 하셔야 작성 가</h5>
     	</c:if>
	     <c:if test="${!empty user}">
	     	<a href="user_logout" class="fontcolor">로그오프</a><br>
      		<a href="user_modify">회원정보수정</a>
       		<a href="">회원탈퇴</a>
	     </c:if>
    
       </font>
     </td>
   </tr>
 </table>
 </body>
 </html>
