<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The way back home - login</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="script/member.js"></script>
</head> 
<body>
	<div class="outwrap">
		<div class="inwrap" style="margin-top: 100px">
		<div class="form">
			<form action="login.do" method="post" name="frm">
				<table class="w3-table">
					<img src="./img/twbh_txt.png" width="250px">
					<tr></tr>
					<tr>
						<td id="label"><label for="userid">ID</label></td>
					</tr>
					<tr>
						<td><input type="text" name="userid" id="userid" class="w3-input w3-border w3-round" value="${userid}">
						</td>
					</tr>
					<tr>
						<td id="label"><label for="pwd">P/W</label>
						</td>
					</tr>
					<tr>
						<td><input type="password" id="pwd" name="pwd" class="w3-input w3-border w3-round" >
						</td>
					</tr>

					<tr>
						<td><input type="submit"value="로그인" onclick="return loginCheck()" class="w3-button w3-round w3-dark-grey"  ></td>
					</tr>
					<tr>
						<td><input type="button" value="회원 가입" onclick="location.href='join.do'" class="w3-button w3-round w3-light-grey" ></td>
					</tr>
					<tr>
						<td colspan="2">${message}</td>
					</tr>
				</table>
			</form>
			
		
		</div>
		<div class="img">
		<figure class="snip1445"><img src="./img/index_img.jpg" width="440px"  height="440px"/>
		  <figcaption>
		    <div>
		      <h2>L o g i n</h2>
		     
		    </div>
		  </figcaption>
		  <a href="#"></a>
		</figure>
	
		</div>
		</div>
	</div>
</body>
</html>