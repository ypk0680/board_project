<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="script/member.js"></script>
<title>The way back home - 회원 관리</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="outwrap">
<div class="inwrap">
	
	<h3>회원 수정</h3>
	<p>*회원 정보 변경 시 비밀번호는 반드시 입력해주세요.</p>
	<form action="memberUpdate.do" method="post" name="frm">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="20"
					value="${mVo.name}" readonly></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" size="20"
					value="${mVo.userid}" readonly></td>
			</tr>
			<tr>
				<td>암 &nbsp; 호</td>
				<td><input type="password" name="pwd" size="20"></td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td><input type="password" name="pwd_check" size="20"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="20"
					value="${mVo.email}"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" size="20"
					value="${mVo.phone}"></td>
			</tr>
			<tr><td>  </td></tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="확인"
					onclick="return joinCheck()" class="w3-button w3-round w3-green" >
					&nbsp;&nbsp; 
					<input type="button" value="취소" onclick="history.back(-1);" class="w3-button w3-round w3-dark-grey" >
				</td>
			</tr>
		</table>
	</form>
	<br><br><br><br>
</div>
</div>
</body>
</html>