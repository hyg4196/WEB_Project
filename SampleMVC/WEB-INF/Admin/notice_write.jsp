<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>공지사항 관리 - 관리자페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
<!--
body,td,th {
	font-size: 12px;
	color: #606060;
}
body {
	margin-left: 0px;
	margin-top: 0px;
}
-->

</style>

<script>
	function send() {
		notice.submit();
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
					<td height=40 align="center" style="font-size: 15px;"><b>공지사항 
					<c:if test="${check eq 'write' }">
					[쓰기]
					</c:if>
					<c:if test="${check eq 'modify' }">
					[수정]
					</c:if>
					</b></a>
					</b></td>
				</tr>
			</table><br>
			<form name="notice" method="post" action="Notice?cmd=notice_write&page=${page}">
			<table width="60%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><table width="100%" border="0" cellpadding="6" cellspacing="1" bgcolor="DDDDDD">
							<tr>
								<td width="20%" align="center" bgcolor="EcECEC"><strong>제목</strong></td>
								<td bgcolor="ffffff"><input name="subject" type="text" value=""  style="width:450; height:18; padding:2; border:1 solid slategray" size="120"></td>
							</tr>
							<tr bgcolor="EcECEC">
								<td align="center" bgcolor="EcECEC"><strong>내용</strong></td>
								<td bgcolor="ffffff"><textarea name="contents" cols="10" rows="10" style="width:490; height:200; padding:2; border:1 solid slategray" tabindex="2"></textarea></td>
							</tr>
						</table>
					</td>
				</tr>

			</table><br>
			<table width="60%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align=center><a href="javascript:send()"><b>
					<c:if test="${check eq 'write' }">
					[등록]
					</c:if>
					<c:if test="${check eq 'modify' }">
					[수정]
					</c:if></b></a>&nbsp; <a href="javascript:history.back()"><b>[취소]</b></a></td>
				</tr>
			</table>
			</form>
</body>
</html>
