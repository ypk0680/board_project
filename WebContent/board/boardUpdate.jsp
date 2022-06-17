<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty loginUser}">
	<jsp:forward page='login.do' />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The way back home</title>
<link rel="stylesheet" type="text/css" href="css/board_rw.css">
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
	<%
	 	session.setAttribute("command", "board_update");  // multipart/form-data때문에 세션을 통해 커맨드 전송
	%>
	<jsp:include page="../header.jsp"></jsp:include>
	<div id="wrap" align="center">
		
		<p>게시글 수정</p>
		<form name="frm" method="post" action="BoardServlet" enctype="multipart/form-data">
			<input type="hidden" name="command" value="board_update"> 
			<input type="hidden" name="num" value="${board.num}">
			<table id="list">
				<tr>
					<th class="w3-pale-green">작성자</th>
					<td><input type="text" size="12" name="name"
						value="${board.name}"> * 필수</td>
				</tr>
				<tr>
					<th class="w3-pale-green">이메일</th>
					<td><input type="text" size="40" maxlength="50" name="email"
						value="${board.email}"></td>
				</tr>
				<tr>
					<th class="w3-pale-green">사 진</th>
					<td><input type="file" name="pictureUrl"><br>
						(주의사항 : 이미지를 변경하고자 할때만 선택하시오)</td>
				</tr>
				<tr>
					<th class="w3-pale-green">제목</th>
					<td><input type="text" size="70" name="title"
						value="${board.title}"></td>
				</tr>
				<tr>
					<th class="w3-pale-green">내용</th>
					<td><textarea cols="70" rows="15" name="content">${board.content}</textarea></td>
				</tr>
			</table>
			<br> <input type="submit" value="등록" onclick="return boardCheck()"> 
				<input type="reset" value="다시 작성"> 
				<input type="button" value="목록" onclick="location.href='BoardServlet?command=board_list'"><br><br>
		</form>
	</div>
</body>
</html>