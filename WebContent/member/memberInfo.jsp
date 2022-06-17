<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty loginUser}">
	<jsp:forward page='login.do' />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<script type="text/javascript" src="script/member.js"></script>
<title>The way back home - 회원 정보</title>
</head>
<body>
<div class="outwrap">
<div class="inwrap">
			<img src="../img/twbh_txt.png" width="250px">
	<h2>회원 정보 </h2>
	<form action="../logout.do">
		<table cellpadding="7px">
			<tr>
				<td>안녕하세요. ${loginUser.name}(${loginUser.userid})님</td>
				
			</tr>
			<tr>
				<td><a href="../login.do"> 메인페이지로 </a>&nbsp; &nbsp;  <a href="../BoardServlet?command=board_list&page=1"> 게시판으로 </a></td>
			</tr>
			<tr><td> </td></tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"value="로그아웃" class="w3-button w3-round w3-dark-grey" > 
				<input type="button" value="회원정보변경" onclick="location.href='../memberUpdate.do?userid=${loginUser.userid}'" class="w3-button w3-round w3-dark-grey" >
				<input type="button" value="회원탈퇴" onclick="location.href='../memberDrop.do?userid=${loginUser.userid}'" class="w3-button w3-round w3-dark-grey" >
				
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</body>
</html>