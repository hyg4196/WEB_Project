<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>공지사항 관리 - 관리자페이지</title>
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
			history.back();
		</script>
     	 </c:if>
</c:if>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td><jsp:include page="top_menu.jsp" flush="true" /></td></tr>
	<tr>
		<td align="center" height="100%" valign=middle><br>
			<table width="30%" border="1" cellspacing="0" cellpadding="3" bgcolor="#FFCC66" bordercolor="#FFFFFF" bordercolorlight="#000000">
				<tr> 
					<td height=40 align="center" style="font-size: 15px;"><b>공지사항 내용보기</b></a>
					</b></td>
				</tr>
			</table><br>
			<table width="60%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><table width="100%" border="0" cellpadding="6" cellspacing="1" bgcolor="DDDDDD">
							<tr>
								<td align="center" bgcolor="EcECEC"><strong>제목</strong></td>
								<td colspan=3 bgcolor="ffffff">안녕하세요</td>
							</tr>
							<tr>
								<td width="20%" align="center" bgcolor="EcECEC"><strong>등록일</strong></td>
								<td width="40%" bgcolor="ffffff">2007-11-11</td>
								<td width="20%" align="center" bgcolor="EcECEC"><strong>조회수</strong></td>
								<td align="center" bgcolor="ffffff">3</td>
							</tr>
							<tr bgcolor="EcECEC">
								<td align="center" bgcolor="EcECEC"><strong>내용</strong></td>
								<td colspan=3 bgcolor="ffffff">하하하하핳</td>
							</tr>
						</table>
					</td>
				</tr>
			</form>
			</table><br>
			<table width="60%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align=center><a href=""><b>[수정]</b></a>&nbsp; <a href=""><b>[삭제]</b></a>&nbsp; <a href=""><b>[목록]</b></a></td>
				</tr>
			</table>
</body>
</html>
