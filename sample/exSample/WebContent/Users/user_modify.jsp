<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<title>회원등록</title>
<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000; BACKGROUND-POSITION: left top; BACKGROUND-REPEAT: no-repeat;}
-->
.formbox {
	BACKGROUND-COLOR: #F0F0F0; FONT-FAMILY: "Verdana", "Arial", "Helvetica", "돋움"; FONT-SIZE:9pt
} 
--->
</STYLE>
<script>
	function send() {
		if(insert.passwd.value == ""){
			alert("비밀번호를 입력하세요.");
			insert.passwd.focus();
			return
		}else if(insert.newpasswd.value == ""){
			alert("변경할 비밀번호를 확인을 해주세요.");
			insert.newpasswd.focus();
			return
		}else if(insert.newrepasswd.value != insert.newpasswd.value){
			alert("변경할 비밀번호를 제대로 확인하세요.");
			insert.newrepasswd.focus();
			return
		}
		
		insert.submit();
		
	}
	
	function reset() {
		alert("test");
	}
	
</script>
</head>

<body bgcolor="#FFFFFF" LEFTMARGIN=0  TOPMARGIN=0 >
 
 <!-- 탑 메뉴 영역 삽입--><%@ include file="/Include/topmenu.jsp" %>
 


<table border="0" width="800">
<tr>
  <td width="20%"  bgcolor="#ecf1ef" valign="top" >
	
	<!--로그인 영역 삽입-->	<%@ include file="/Include/login_form.jsp" %>
	

  </td>
  <td width="80%" valign="top" style="margin-left: 200px">&nbsp;<img src="Users/img/title1.gif" ><br>    
	<form name="insert" method=post action="user_modify">
	<table border=0 cellpadding=0 cellspacing=0 border=0 width=730 valign=top>
		<tr><td align=center><br>                            
			<table cellpadding=0 cellspacing=0 border=0 width=650 align=center>       
				<tr>
					<td bgcolor="#7AAAD5">            
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td align=left BORDER="0" HSPACE="0" VSPACE="0"><img src="Users/img/u_b02.gif"></td>
								<td align=center bgcolor="#7AAAD5"><FONT COLOR="#FFFFFF"><b>사용자등록&nbsp;</b><font color=black>(</font><font color=red>&nbsp;*&nbsp;</font><font color=black>표시항목은 반드시 입력하십시요.)</font></FONT></td>
								<td align=right BORDER="0" HSPACE="0" VSPACE="0"><img src="Users/img/u_b03.gif"></td>
							</tr>
						</table>
						<table cellpadding=3 cellspacing=1 border=0 width=100%>
							<tr>
								<td width=110 bgcolor=#EFF4F8>&nbsp;회원 성명<font color=red >&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=name size=16 maxlength=20 value="${user.name }" readonly>>성명은 빈칸없이 입력하세요.
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;회원 ID<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<table cellspacing=0 cellpadding=0>
										<tr>
											<td align=absmiddle>
												<input type=text name=userid size=12 maxlength=16 value="${user.userid }" readonly style="width:120" >
											</td>
											<td>
                  								5~16자 이내의 영문이나 숫자만 가능합니다.
                  							</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;비밀번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
								<input type=password name=passwd size=8 maxlength=12 style="width:80">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;새로운비밀번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE><input type=password name=newpasswd size=8 maxlength=12 value="" style="width:80"> </td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;새로운비밀번호확인<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE><input type=password name=newrepasswd size=8 maxlength=12 value="" style="width:80">비밀번호 확인을 위해서 비밀번호를 한번 더 입력해주세요. </td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;전화번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=tel size=13 maxlength=13 value="${user.tel }">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;E-mail
                					<font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE valign=middle>
									<input type="text" name="email" maxlength="15" value="${user.email }" readonly >
								</td>
							</tr>
						</table>
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td valign=bottom>
									<img src="Users/img/u_b04.gif" align=left hspace=0 vspace=0 border=0>
								</td>
								<td align=center></td>
								<td valign=bottom>
									<img src="Users/img/u_b05.gif" align=right hspace=0 vspace=0 border=0>
								</td>
							</tr>
							<tr bgcolor=#ffffff>
								<td colspan=3 align=center>
									<input type="button" value="수정" onclick="send()">
									<input type="button" value="다시쓰기" onclick="">
								</td>
							</tr>
						</table> 
					</td>
				</tr>
				</td>
			</tr>
		</table>
	</form>
	</td>
</tr>
</table>

 <!-- copyright 영역 삽입-->
  	<%@ include file="/Include/copyright.jsp" %>

</body>
</html>
