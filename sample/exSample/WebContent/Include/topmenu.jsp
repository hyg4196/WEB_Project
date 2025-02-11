<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
 <head><title>Web Programming Test</title>
 <link rel="stylesheet" type="text/css" href="/stylesheet.css">
 <style type="text/css">
   .menu, td {font-size:9pt;}
   	.white:link { color: white; text-decoration: none;}
 	.white:visited { color: white; text-decoration: none;}
	 .white:hover { color: white; text-decoration: underline;}
   
 </style>
 <script>
 	function ready() {
		alert("해당 페이지는 현재 준비중입니다.");
	}
 	
 	function send23() {
		if(saerch02.ser.value == "gong"){
			alert("잉!");
		}
	}
 	
 	function login() {
		alert("로그인 하셔야 이용이 가능합니다.");
	}
 	
 	function no() {
		alert("권한이 없습니다.");
	}
 </script>
</head>
 <body text="black" link="blue" vlink="purple" alink="red" bgcolor="white" topmargin="0" leftmargin="0">
 <table border="0" cellspacing="2" bgcolor="#ecf1ef" width="900" bordercolordark="#ffffff" bordercolorlight="#000000">
   <tr>
     <td width="20%" rowspan="4" valign="bottom" bgcolor="#000000">
       <a href="index.do"><img src="Include/img/ceo.jpg" width="158" height="83" border="0"></a>
       <font style="font-size:9pt;color:white;font-family:돋움"><b>Total 136,489
       <font color="yellow"></font>
       | Now 178
       <font color="yellow"></font>
       </b></font>
     </td>
     <td bgcolor="#9966ff" width="10%" height="25" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <c:if test="${empty user}">
     <p align="center"><font color="white" size="2"><b><a href="user_login" class="white">로그인</A></b></font></p>
     </c:if>
     <c:if test="${!empty user}">
     <p align="center"><font color="white" size="2"><b><a href="user_logout" class="white">로그아웃</A></b></font></p>
     </c:if>
     
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <c:if test="${empty user}">
	     <p align="center"><font color="white" size="2"><b><a href="user_insert" class="white">회원가입</a></b></font></p>
     </c:if>
     <c:if test="${!empty user}">
    	 <p align="center"><font color="white" size="2"><b><a href="user_modify" class="white">정보수정</a></b></font></p>
     </c:if>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b><a href="javascript:ready()" class="white">공지사항</a></b></font></p>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b><a href="board_list.do" class="white">게시판</b></font></p>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b><a href="javascript:ready()" class="white">자료실</a></b></font></p>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b><a href="guest_list.do" class="white">방명록</a></b></font></p>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b><a href="javascript:ready()" class="white">겔러리</a></b></font></p>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b>
    
     <c:if test="${user.userid eq 'admin' }">
  			<a href="admin_BoardList.do" class="white">
     </c:if>
     <c:if test="${empty user }">
     		<a href="javascript:login()" class="white">
     </c:if>
      <c:if test="${! user.userid eq 'admin' }">
     		<a href="javascript:no()" class="white">
     </c:if>
     	관리자</a></b></font></p>
     </td>
   </tr>                   
   <tr>
     <td colspan="8">
     <p><img src="Include/img/bar-01.gif" width="100%" height="1" border="0"></p>
     </td>
   </tr>
   <tr>
     <td colspan="8"><p><img src="Include/img/bar-01.gif" width="100%" height="1" border="0"></p></td>
   </tr>
   <tr>
     <td><p><b><span style="font-size:15pt;">&nbsp;Search</span></b></p></td>
     <form name="search02" action="index.do" method="post">
     <td colspan="5">
     <p>&nbsp;
       <Select name="ser" size="1">
         <option value="gong">공지사항</option>
         <option value="board">게시판</option>
         <option value="guest">방명록</option>
         <option value="ja">자료실</option>
       </Select>
       <Select name="search" size="1">
         <option value="name">작성자</option>
         <option value="subject">제목</option>
         <option value="contents">내용</option>
       </Select>
         <input type="text" name="key" size="12">
         <input type="image" src="Include/img/search2.gif" onClick="send23()">
     </td>
     </form>
   </tr>
 </table>  
 </body>
 </html>
