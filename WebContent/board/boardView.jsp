<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
	<div id="wrap" align="center">
			<table id="list">
				<tr>
					<th class="w3-pale-green">작성자</th>
					<td>${board.name}</td>
					<th class="w3-pale-green">이메일</th>
					<td>${board.email}</td>
				</tr>
				<tr>
					<th class="w3-pale-green">작성일</th>
					<td><fmt:formatDate value="${board.writedate}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
					<th class="w3-pale-green">조회수</th>
					<td>${board.readcount }</td>
				</tr>
				<tr>
					<th class="w3-pale-green">제목</th>
					<td colspan="3">${board.title }</td>
				</tr>
				<tr>
					<th class="w3-pale-green">내용</th>
					
					<td colspan="3">
					
					<c:if test="${board.pictureUrl !=null}">
						<img src="./upload/${board.pictureUrl}" style="width: 600px; height:auto;">
					</c:if>
					<pre>${board.content }</pre></td>
				</tr>
			</table>
			<br>
			<!-- 좋아요 ajax -->
			<form id="like_form">
				<table id="list">
					<input type="hidden" name="command" value="like_it">
					<input type="hidden" name="board_num" value="${board.num}">
					<tr><input type="button" value="좋아요!" onclick="return like()" > </tr>
					<tr><div id="like_result">${board.like_it}</div> </tr>
				</table>
			</form>
			<br>
			<table id="list">
				<!-- 댓글 list -->
				<c:forEach var="cmt" items="${cmt_list }">
					<tr class="record">
						<td align="center">${cmt.userid}</td>
						<td>${cmt.content} </a></td>
						<td>${cmt.writedate}</td>
					</tr>
				</c:forEach>
				
				
				<!-- 댓글 폼 -->
				<tr bgcolor="#F5F5F5">
		            <form id="writeCommentForm" name="writeCommentForm" method="post" action="BoardServlet">
		           	 	<input type="hidden" name="command" value="board_comment">
		           	 	<input type="hidden" name="comment_id" value="${loginUser.userid}">
		                <input type="hidden" name="board_num" value="${board.num}">
		                <!-- 아이디-->
		                <td width="150" >
		                    <div align="center">
		                        ${loginUser.userid}
		                    </div>
		                </td>
		                <!-- 본문 작성-->
		                <td width="550">
		                    <div>
		                        <textarea name="comment_content" id="comment_content" rows="4" cols="70" ></textarea>
		                    </div>
		                </td>
		                <!-- 댓글 등록 버튼 -->
		                <td width="100">
		                    <div id="btn" style="text-align:center;">
		                    	<input type="submit" value="댓글 등록"> 
		                    	
		                    </div>
		                </td>
		            </form>
	            </tr>
	
			<tr></tr>
			</table>
		
		
			<!-- 버튼 -->	
			<%
			  	String cpage = request.getParameter("page"); 
			%>
			<table id="button">
				<tr>
					<td>
						<input type="button" value="게시글 리스트"
							onclick="location.href='BoardServlet?command=board_list&page=<%=cpage %>'" > 	
						<c:set var="aa" value="${board.title}"/>
					</td>
					
					<td id="td2">
						<input type="button" value="글쓰기"
							onclick="location.href='BoardServlet?command=board_write_form&num=${board.num}'">
				
						<c:choose>
						  <c:when test="${fn:contains(aa,'[Re]')}">
						  </c:when>
						  <c:otherwise>
						    <input type="button" value="답글"
							onclick="location.href='BoardServlet?command=board_reply_form&num=${board.num}'">
						  </c:otherwise>
						</c:choose>
						
						<c:if test="${loginUser.userid == board.name}">
							<input type="button" value="게시글 수정"
								onclick="location.href='BoardServlet?command=board_update_form&num=${board.num}'">
							<input type="button" value="게시글 삭제"
								onclick="return del_check(${board.num})">
						</c:if>
	
					</td>
				</tr>
			</table>
	</div>
	<br><br>
</body>
</html>