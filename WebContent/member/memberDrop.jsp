<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<title>The way back home - 회원 탈퇴</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="outwrap">
	<div class="inwrap">
		<form action="memberDrop.do?userid=${loginUser.userid}" method="post" >
			${userid} 님 정말로 탈퇴 하시겠습니까? <br>
			탈퇴하면 다시 복구 안됩니다.<br><br>
			<input type="submit" value="탈퇴"  class="w3-button w3-round w3-dark-grey" >
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="취소" onclick="location.href='BoardServlet?command=board_list'"  class="w3-button w3-round w3-green" >
		</form>
	</div>
</div>
</body>
</html>