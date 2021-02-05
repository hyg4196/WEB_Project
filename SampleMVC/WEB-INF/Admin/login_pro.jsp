<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /1 = 성공 , 0 - 비번 오류 , -1 = id 오류
 -->
<c:if test="${row==1 }">
	<script>
		alert("로그인 !");
		location.href="Admin?cmd=admin_boardList";
	</script>
</c:if>
<c:if test="${row==0 }">
	<script>
		alert("로그인 실패\n! 비밀번호 오류!");
		history.back();
	</script>
</c:if>
<c:if test="${row==-1 }">
	<script>
		alert("로그인 실패\n! 아이디 오류!");
		history.back();
	</script>
</c:if>
<c:if test="${row==2 }">
	<script>
		alert("접근 권한이 없는 사용자입니다.");
		location.href="index";
	</script>
</c:if>