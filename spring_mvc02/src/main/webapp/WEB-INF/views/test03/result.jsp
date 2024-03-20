<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>결과보기</h2>
	<h3>
		<!-- jsp 방식 -->
		<li>올린사람 : ${name}</li>
		<li>파일이름 : ${f_name}</li>
		<li>파일타입 : ${f_type}</li>
		<li>파일크기 : ${size}KB</li>
		<li>수정날짜 : ${lastday}</li>
		<!-- 위치와 파일 이름을 파라미터로 사용하지만 위치가 고정되어있어 파일 이름만 사용 -->
		<li>다운로드 : <a href="down.do?f_name=${f_name}"><img src="resources/images/${f_name}"></a></li>
		<li>다운로드 : <a href="down2.do?f_name=${f_name}"><img src="resources/images/${f_name}"></a></li>
	</h3>
</body>
</html>