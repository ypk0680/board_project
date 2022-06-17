<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty loginUser}">
	<jsp:forward page='login.do' />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>The way back home - main</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="script/parallax.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="parallax_window_div1" data-parallax="scroll" data-image-src=""> 
	<br><br><br>
	
	<div class="div2">
		<h1>The Way Back Home?</h1>
		<p> The Way Back Home은 </p>
		<p>  오늘 하루도 열심히 살아가는 <b>직장인들을 위한 커뮤니티</b> 입니다.</p>
	</div>
</div>

<div class="outwrap">
<div class="inwrap" style="margin-top: 10px">
	<br>
	'${loginUser.userid}'님 환영합니다 ^^*  &nbsp; 
			<input type="button" value="내정보" onclick="location.href='member/memberInfo.jsp'" class="w3-button w3-round w3-padding-small w3-dark-grey">
			<input type="button" value="로그아웃" onclick="location.href='logout.do'" class="w3-button w3-round w3-padding-small w3-dark-grey"><br>
	
		<br>
			<div class="contents1">
				<figure class="snip1384">
				  <img src="img/sky.jpg" alt="sample83"  height="430px"/>
				  <a>
				    <h2>오늘의 날씨 보기 ( 수정 필요 )</h2>
				    <p>Which is worse, that everyone has his price, or that the price is always so low.</p><i class="ion-ios-arrow-right"></i>
				  </figcaption>
				  <a href="weather.jsp"></a>
				</figure>
			</div>
			<div class="contents2">
				<figure class="snip1384">
				  <img src="img/board.jpg" alt="sample83" height="430px" />
				  <figcaption>
				    <h2>게시판 바로 가기</h2>
				    <p>Which is worse, that everyone has his price, or that the price is always so low.</p><i class="ion-ios-arrow-right"></i>
				  </figcaption>
				  <a href="BoardServlet?command=board_list"></a>
				</figure>
			</div>
</div>
</div>
<br><br><br>
</body>


<script type="text/javascript">
$('.parallax_window_div1').parallax({imageSrc: 'img/photo1.jpg'});
</script>

</html>