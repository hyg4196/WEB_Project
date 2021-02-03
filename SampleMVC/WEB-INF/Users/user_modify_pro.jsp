<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${row==1 }">
	<script>
		alert("수정 성공\n 다시 로그인 해주세요.");
		location.href="index";
	</script>
</c:if>
<c:if test="${row==0 }">
	<script>
		alert("수정 실패");
		history.back();
	</script>
</c:if>