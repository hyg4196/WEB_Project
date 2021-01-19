<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<<c:choose>
	<c:when test="${row==1}">
		<script>
			alert("수정 되었습니다");
			location.href="board_list.do?page=${page}";
		</script>	
	</c:when>
	<c:when test="${row==0 }">
		<script>
			alert("수정 실패 \n! 비밀번호를 다시 확인해주세요 !");
			history.back();
		</script>
	</c:when>
</c:choose>

    
<%-- <%
	int row = (int) request.getAttribute("row");	
	if(row == 1){
%>
	<script>
		alert("수정 되었습니다");
		location.href="board_list.do?page=${page}";
	</script>	
	
<%	
	}else{
%>
	<script>
		alert("수정 실패 \n! 비밀번호를 다시 확인해주세요 !");
		history.back();
	</script>

<%
	}
%> --%>