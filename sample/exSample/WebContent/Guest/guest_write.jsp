<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/Include/topmenu.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <head><title>방명록 작성</title>
    <link rel="stylesheet" type="text/css" href="stylesheet.css">
  <script>
  	function send(){
  		if(guest.subject.value == ""){
			alert("제목을 입력하세요.");
			guest.subject.focus();
			return;
		}else if(guest.contents.value == ""){
			alert("내용을 입력하세요.");
			guest.contents.focus();
			return;
		}else if(guest.pass.value == ""){
  			guest.pass.value="${user.passwd}";
			guest.cnt.value = "1";
		}
  		guest.action = "guest_write.do";
  		guest.submit();
  	}
  </script>	
</head>
 <body topmargin="0" leftmargin="0">
  <c:if test="${empty user}">
 	<script>
 		alert("글쓰기는 로그인 상태만 가능합니다.");
 		history.back();
 	</script>    
 </c:if>
 <table border="0" width="800">
 <tr>

   <td width="20%" height="500" bgcolor="#ecf1ef" valign="top">
   <!-- 다음에 추가할 부분 -->	<%@ include file="/Include/login_form.jsp" %>
   
   </td>
   <td width="80%" valign="top">&nbsp;<br>
     <img src="Guest/img/bullet-01.gif"><font size="3" face="돋움" color="blue"> <b>방명록</b></font>
     <font size="2"> - 글쓰기</font><p>
     <img src="Guest/img/bullet-03.gif"><font size="2" face="돋움" color="orange"> 잠깐</font> &nbsp;
     <img src="Guest/img/bullet-02.gif"><font size="2" face="돋움">는 필수 입력 사항입니다.</font><p>
     <form name="guest" method="post">
     <input type="hidden" size="20" name="cnt" value="0">
     <input type="hidden" name = "page" value="${ page}">
      <table border="0">
       <tr>
         <td width="5%" align="right"><img src="Guest/img/bullet-02.gif"></td>
         <td width="15%"><font size="2 face="돋움"">글쓴이</font></td>
         <td width="80%">
         <input type="text" size="20" name="name" value="${user.userid}" readOnly></td>
       </tr>
       <tr>
         <td align="right"><img src="Guest/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">제목</font></td>
         <td><input type="text" size="60" name="subject"></td>
       </tr>
       <tr>
         <td align="right"><img src="Guest/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">내용</font></td>
         <td><textarea wrap="physical" rows="10" name="contents" cols="60"></textarea></td>
       </tr>
       <tr>
         <td align="right"><img src="Board/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">비밀번호</font></td>
          <td><input type="password" size="10" name="pass">
		  <font size="2" face="돋움">*게시판 전용 비밀번호입니다. <br> 
		  <div style="margin-left: 110px">입력이 없을시 계정 비밀번호로 자동 설정됩니다.</div></font></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td><font size="2">&nbsp;</font></td>
          <td>
                     <a href="javascript:send()">◀등 록▶</a>&nbsp;&nbsp;&nbsp;
                     <a href="javascript:history.back()">◀취 소▶</a>
          </td>
        </tr>
      </table>
      </form>
    </td>
  </tr>
  </table>
  </body>
  </html>
