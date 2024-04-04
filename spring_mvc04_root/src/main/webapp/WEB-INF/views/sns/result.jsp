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
			url : "kakaoUser.do",
			method : "post",
			dataType : "text",
			success : function(data) {
				let users = data.split("/");
	            $("#res").append(users[1] + "(" + users[2] + ")" + "님 환영합니다.")
			},
			error : function() {
				alert("읽기실패");
			}
		});
	});
</script>	
</head>
<body>
	<h1>kakao 로그인 결과</h1>
    <div id="res"></div>
    <!-- 다음 로그아웃 : 내 애플리케이션 - 제품설정 - 카카오 로그인 - 고급 - 로그아웃 리다이렉트 API 등록 -->
    <!-- 문서 - 로그인 - REST API - 오른쪽 메뉴 - 카카오계정과 함께 로그아웃 -->
    <a href="https://kauth.kakao.com/oauth/logout?client_id=64759d5d6ee33a3b6d20bdc9b08cd4c0&logout_redirect_uri=http://localhost:8090/kakaologout.do">
		로그아웃
	</a>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>