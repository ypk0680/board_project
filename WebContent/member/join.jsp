<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The way back home-회원 가입 </title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
<div class="outwrap">
<div class="inwrap">
<jsp:include page="../header.jsp"></jsp:include>
<h3>회원 가입</h3>
	'*' 표시 항목은 필수 입력 항목입니다.
	<form action="join.do" method="post" name="frm">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="20">*</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" size="20"  id="userid">* </td>
				<td><input type="hidden" name="reid" size="20"> <input type="button" id="re_check" value="중복 체크" onclick="idCheck()" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
			</tr>
			<tr>
				<td>암 호</td>
				<td><input type="password" name="pwd" size="20">*</td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td><input type="password" name="pwd_check" size="20">*</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="20"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" size="20"></td>
			</tr>
			<tr>
				<td>등급</td>
				<td><input type="radio" name="admin" value="0"
					checked="checked"> 일반회원 </td>
			</tr>
			<tr>
				<td colspan="2">${message }</td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan="3" align="center"><input type="submit" value="확인" onclick="return joinCheck()" class="w3-button w3-round w3-green">
					&nbsp;
					<input type="button" value="취소" onclick="history.back(-1);" class="w3-button w3-round w3-dark-grey">
				</td>
			</tr>
			
		</table>
	</form>
</div>
</div>
</body>
</html>