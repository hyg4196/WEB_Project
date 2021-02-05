<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>협력업체 관리 - 관리자페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css"> 
<!-- 
body,td,tr,table{font-size:9pt; font-family:tahoma;color:#666666;line-height:160%;} 

A:link {font-family:tahoma;font-size:9pt;color:#666666;text-decoration:none;} 
A:visited {font-family:tahoma;font-size:9pt;color:#666666;text-decoration:none;} 
A:active {font-family:tahoma;font-size:9pt;color:#666666;text-decoration:none;} 
A:hover {font-family:tahoma;font-size:9pt;color:#009900;text-decoration:underline;} 
--> 
</style> 

<script>
function search() {
	if(board.key.value == ""){
		alert("검색어 입력");
		board.key.forcous;
		return;
	}
	b_search.submit();
}
</script>

</head>

<body>
<c:if test="${empty user }">
		<script>
			alert("로그인을 해주세요.");
		</script>
     	 <meta http-equiv="refresh" content="0;url=/index">
</c:if>
<c:if test="${!empty user }">
		<c:if test="${user.accessnum != 1}">
		<script>
			alert("접근 권한이 없습니다.");
		</script>
     	 <meta http-equiv="refresh" content="0;url=/index">
     	 </c:if>
</c:if>



<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td><jsp:include page="top_menu.jsp" flush="true" /></td></tr>
	<tr>
		<td align="center" height="100%" valign=middle><br>
								<table width="30%" border="1" cellspacing="0" cellpadding="3" bgcolor="#FFCC66" bordercolor="#FFFFFF" bordercolorlight="#000000">
									<tr> 
										<td height=40 align="center" style="font-size: 15px;"><b>자유게시판</b></a>
										</b></td>
									</tr>
								</table><br>
								<table width="80%" border="0" cellspacing="0" cellpadding="0">
									<tr>
                    <td height="20">* 총 등록수 : <font color=red>${totcount }</font> 건 - ${page}/${totpage } Pages</td>
                  </tr>
                  <c:if test="${empty list }">
					<tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
					<td align="center" height="25">
					<font face="돋움" size="2" color="#000000">등록된 자료가 없습니다.</font></td>
					</tr>
			</c:if>
                  <tr>
                    <td><table width="100%" border="0" cellpadding="6" cellspacing="1" bgcolor="DDDDDD">
                      <tr bgcolor="EcECEC">
                        <td width="15%" align="center" bgcolor="EcECEC"><strong>번호</strong></td>
												<td align="center" bgcolor="EcECEC"><strong>제목</strong></td>
												<td width="15%" align="center"><strong>글쓴이</strong></td>
                        <td width="20%" align="center"><strong>접수일</strong></td>
                        <td width="10%" align="center"><strong>조회수</strong></td>
                      </tr>
                      <tr>
               	<c:forEach var="list" items="${list }">
                        <td align="center" bgcolor="#FFFFFF">${listcount }</td>
                        <td bgcolor="#FFFFFF"><a href="Admin?cmd=admin_boardView">${list.subject }</a></td>
                        <td align="center" bgcolor="#FFFFFF">${list.name}</td>
												<td align="center" bgcolor="#FFFFFF">${list.regdate.substring(0,10) }</td>
												<td align="center" bgcolor="#FFFFFF">${list.readcnt }</td>
                      </tr>
                      	<c:set var="listcount" value = "${listcount-1 }"></c:set>
                     </c:forEach>
	          
	                     <tr>
                        <td height="35" colspan="10" align="center" bgcolor="#FFFFFF">
                        <div align="center">${pageSkip}</div>
                        </td>
                      </tr>
											<form action="Admin?cmd=admin_boardList" method="post" name="b_search">
                      <tr>
                        <td colspan="10" align="center" bgcolor="#FFFFFF"><table width="610" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width=80% height="30" colspan="2" align="right">
																<select name="search" class="textfield">
																		<option value="subject"<c:if test="${search=='subject'}"> select</c:if>  >글제목</option>
																		<option value="name" <c:if test="${search=='name'}"> select</c:if> >작성자</option>
																		<option value="contents" <c:if test="${search=='contents'}"> select</c:if> >글내용</option>
																</select>
																<input name="key" type="text" class="textfield" size="30"></td>
                              <td width=20% align="right"><b> <a href="javascript:search()">[검색]</a></b>  &nbsp;<a href=""><b>[글쓰기]</b></a></td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
</body>
</html>
