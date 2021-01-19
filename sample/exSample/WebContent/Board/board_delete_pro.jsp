<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int row = (int) request.getAttribute("row");	
	if(row == 1){
%>
	<script>
		alert("삭제 되었습니다");
		opener.location.href="board_list.do?page=${page}";
		self.close();
	</script>	
	
<%	
	}else{
%>
	<script>
		alert("삭제 실패\n! 비밀번호를 다시 확인하세요 !");
		history.back();
	</script>

<%
	}
%>