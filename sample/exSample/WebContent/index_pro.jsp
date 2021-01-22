<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${row == 0}">
	<script>
		alert("해당 페이지는 준비중입니다.");
		location.href="index.do";
	</script>
</c:if>
<c:if test="${row==1}">
	<script>
	location.href="board_list.do?search=${search}&key=${key}";
	</script>
<%-- 	<form name="data" method="post">
	<input type="hidden" name="key" value="${key}">
	<input type="hidden" name="search" value="${search}">
	</form> --%>
</c:if>
<c:if test="${row==2}">
	<script>
		location.href="guest_list.do?search=${search}&key=${key}";
	</script>
</c:if>
    
<%-- <%
	int row = (int) request.getAttribute("row");	
	if(row == 1){
%>
	<script>
		alert("등록되었습니다");
		location.href="board_list.do?page=${page}";
	</script>	
	
<%	
	}else{
%>
	<script>
		alert("등록 실패\n! 등록 정보를 다시 확인해주세요 !");
		history.back();
	</script>

<%
	}
%> --%>