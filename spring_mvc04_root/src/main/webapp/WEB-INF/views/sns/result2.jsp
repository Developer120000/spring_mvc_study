<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#res").empty();
		$.ajax({
			url : "naverUser.do",
			method : "post",
			dataType : "text",
			success : function(data) {
			  let users = data.split("/");
			  $("#res").append(users[0]+"("+users[1] +")" + "님 환영합니다.<br>")
			  $("#res").append(users[2]+"("+users[3] +")" + "님 환영합니다.<br>")
			  $("#res").append(users[4]+"("+users[5] +")" + "님 환영합니다.<br>")
			},
			error : function() {
				alert("읽기실패");
			}
		});
	});
	
	function logout_go() {
		location.href = "naverlogout.do";
	}
</script>	
</head>
<body>
	<h2>Naver 로그인 결과보기</h2>
	<div id="res"></div>
	<input type="button" value="로그아웃" onclick="logout_go()">
</body>
</html>












