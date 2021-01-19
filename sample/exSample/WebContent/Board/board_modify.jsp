<%@page import="exSample.model.BoardVO"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/Include/topmenu.jsp" %>


<%
	BoardVO vo = (BoardVO) request.getAttribute("vo");

%>
   <head><title>게시판 수정</title>
    <link rel="stylesheet" type="text/css" href="/stylesheet.css">
	<script>
	function send() {
		if(board.pass.value==""){
			alert("패스워드를 입력하십시요.");
			board.pass.focus();
			return;
		}
		board.submit();
	}
	</script>
</head>
<c:set var="name" value="<%=vo.getName() %>"></c:set>
<c:if test="${empty user}">
	<script>
		alert("수정 작업은 로그인 이후 가능합니다.");
		history.back();
	</script>
</c:if>
<c:if test="${!empty user}">
	<c:if test="${user.userid ne name}">
		<script>	
			alert("본인이 작성한 글이 아니면 수정 할 수 없습니다.");
			history.back();
		</script>
	</c:if>
</c:if>
 <body topmargin="0" leftmargin="0">
 <table border="0" width="800">
 <tr>
   <td width="20%" height="500" bgcolor="#ecf1ef" valign="top">

   <!-- 다음에 추가할 부분 "-->
   		<%@ include file="/Include/login_form.jsp" %>
   

   </td>
   <td width="80%" valign="top" style="margin-left: 200px">&nbsp;<br>
     <img src="Board/img/bullet-01.gif"><font size="3" face="돋움" color="blue"> <b>자 유 게 시 판</b></font>
     <font size="2"> - 수정하기</font><p>
     <img src="Board/img/bullet-03.gif"><font size="2" face="돋움" color="orange"> 잠깐</font> &nbsp;
     <img src="Board/img/bullet-02.gif"><font size="2" face="돋움">는 필수 입력 사항입니다.</font><p>
     <form method="post" name="board" action="board_modify.do?idx=${idx}&page=${page}" >

      <table border="0">
       <tr>
       <td align="right"><img src="Board/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">글쓴이</font></td>
         <td><input type="text" size="20" name="name" value="<%-- <%=vo.getName() %> --%> <%=vo.getName() %>" readonly></td>
       </tr>
       <tr>
         <td align="right">&nbsp;</td>
         <td ><font size="2 face="돋움"">메일주소</font></td>
         <td>
          <input type="text" size="20" name="email" value="<%=vo.getEmail()%>"></td>
       </tr>
       <tr>
         <td align="right"><img src="Board/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">제 목</font></td>
         <td><input type="text" size="60" name="subject" value="<%=vo.getSubject()%>"></td>
       </tr>
       <tr>
         <td align="right"><img src="Board/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">내용</font></td>
         <td><textarea wrap="physical" rows="10" name="contents" cols="60" ><%=vo.getContents() %></textarea></td>
       </tr>
       <tr>
         <td align="right"><img src="Board/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">비밀번호</font></td>
          <td><input type="password" size="10" name="pass" >
		  <font size="2" face="돋움">*본인 확인을 위한 비밀번호를 입력해주세요.</font></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td><font size="2">&nbsp;</font></td>
          <td><a href="javascript:send()"><img src="Board/img/edit2.gif" border=0></a>&nbsp;
          <a href="javascript:history.back()"><img src="Board/img/cancle.gif" border=0></a></td></tr>
      </table>
      </form>
    </td></tr>
  </table>
  </body>
  </html>
