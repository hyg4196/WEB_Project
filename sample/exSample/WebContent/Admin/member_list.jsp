<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<title>회원목록 보여주기</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
--->
</STYLE>
</head>
<body>
<table width="800" border="1" cellspacing="0" cellpadding="2" bordercolorlight="#173E7C" bordercolordark="white">
  <tr>
    <td width=50 align=center>번호</td>
    <td width=100 align=center>ID</td>
    <td width=100 align=center>이름</td>
    <td width=400 align=center>주소</td>
    <td width=150 align=center>전화번호</td>
    <td width=200 align=center>이메일</td>
    <td width=150 align=center>가입일자</td>
    <td width=150 align=center>최근접속일자</td>
    <td width=100 align=center>삭제여부</td>
    
  </tr>
   <tr>
      <td align=center>9</td>
      <td align=center>ein1027</td>
      <td align=center>홍길동</td>
      <td width=300>대전광역시 중구 도마동 114(집)</td>
      <td align=center>042-222-1111</td>
      <td align=center>aaaa@aaaa.com</td>
      <td align=center>2019-01-01</td>
      <td align=center>2019-03-30</td>
      <td align=center>[삭제]</td>
   </tr>

</table>
<table width=550>
  <tr>
    <td>
      <select name="search_gubun">
        <option >이름 </option>
        <option >주소 </option>
        
    </td>
    <td>  찾는이름:
          <input type="text" name="search_name" size=10> &nbsp;
          <input type=hidden name="user_id" >[조회]</a>
     </td>
   </tr>
</table>    
</body>
</html>
