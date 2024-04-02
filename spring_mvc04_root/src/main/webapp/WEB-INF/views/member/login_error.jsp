<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	// 밑에 글 다 실행하고 나중에 읽힘
	// 처음왔을때는 pwdchk, fail 값이 없어서 실행안함
	// 지금은 jquery 하는데 나중에는 ajax 사용함
	$(document).ready(function() {
		alert("로그인 해야만 사용할 수 있습니다.");
		location.href="loginForm.do";
	});
</script>
<body>
</body>
</html>