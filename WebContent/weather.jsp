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
<link rel="stylesheet" type="text/css" href="css/weather.css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="outwrap">
		<div class="inwrap">
			<h1>오늘의 출퇴근 길은?</h1>
			*기상청 API를 통해 데이터를 가지고 오기 때문에   <br>
			로딩이 늦을 수 있으니 조금만 기다려주세요!<br>
			<a href="login.do">메인페이지</a>
			<a href="BoardServlet?command=board_list">게시판으로</a>
			<div class="form" style="margin-top: 50px">
				
				<table>
					<tr>
						<td><input type="button" value="서울특별시"
							onclick="location.href='weather.do?loc=seoul'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
						<td><input type="button" value="세종특별자치시"
							onclick="location.href='weather.do?loc=sejong'"  class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
					</tr>
					<tr>
						<td><input type="button" value="충청남도"
							onclick="location.href='weather.do?loc=cnamdo'"  class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
						<td><input type="button" value="대구광역시"
							onclick="location.href='weather.do?loc=dg'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
					</tr>
					<tr>
						<td><input type="button" value="인천광역시"
							onclick="location.href='weather.do?loc=ich'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
						<td><input type="button" value="충청북도"
							onclick="location.href='weather.do?loc=cbuckdo'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
					</tr>
					<tr>
						<td><input type="button" value="경상북도"
							onclick="location.href='weather.do?loc=kb'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
						<td><input type="button" value="강원도"
							onclick="location.href='weather.do?loc=kw'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
					</tr>
					<tr>
						<td><input type="button" value="전라남도"
							onclick="location.href='weather.do?loc=jnamdo'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
						<td><input type="button" value="대전광역시"
							onclick="location.href='weather.do?loc=dj'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
					</tr>
					<tr>
						<td><input type="button" value="경기도"
							onclick="location.href='weather.do?loc=kkd'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
						<td><input type="button" value="전라북도"
							onclick="location.href='weather.do?loc=jbuckdo'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
					</tr>
					<tr>
						<td><input type="button" value="경상남도"
							onclick="location.href='weather.do?loc=knamdo'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
						<td><input type="button" value="부산광역시"
							onclick="location.href='weather.do?loc=bs'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
					</tr>
					<tr>
						<td><input type="button" value="울산광역시"
							onclick="location.href='weather.do?loc=us'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
						<td><input type="button" value="광주광역시"
							onclick="location.href='weather.do?loc=kj'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
					</tr>
					<tr>
						<td><input type="button" value="제주특별자치도"
							onclick="location.href='weather.do?loc=jj'" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
						<td><input type="button" value="★" onclick="#" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
					</tr>
				</table>
			</div>
			<c:if test="${!empty locaiton}">
				<div class="img">
					<br>
					<h3>${locaiton }의 날씨</h3>
					<c:choose>
						<c:when test="${sky == '맑음'}">
							<img alt="" src="img/SKY01.png">
						</c:when>
						<c:when test="${sky == '구름조금'}">
							<img alt="" src="img/SKY02.png">
						</c:when>
						<c:when test="${sky == '구름많음'}">
							<img alt="" src="img/SKY03.png">
						</c:when>
						<c:when test="${sky == '흐림'}">
							<img alt="" src="img/SKY04.png">
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${pty == '비'}">
							<img alt="" src="img/SKY05.png">
						</c:when>
						<c:when test="${pty == '비/눈'}">
							<img alt="" src="img/SKY06.png">
						</c:when>
						<c:when test="${pty == '눈'}">
							<img alt="" src="img/SKY07.png">
						</c:when>
					</c:choose>
		
					<h5>하늘은 <b>${sky }</b></h5>
					<h5>강수확률 ${pop }</h5>
	
					<h5>기온 ${t3h }°C</h5>
					<h5>최저 기온 ${tmn }°C</h5>
					<h5>최고 기온 ${tmx }°C</h5>
				</div>
			</c:if>
		</div>
		
		
	</div>
</body>
</html>