<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>결과보기</h2>
	<h2> ${s1} ${op} ${s2} = ${result} </h2>
	<h2> ${cPage}</h2>
	<hr>
	
	<h2>결과보기</h2>
	<h2> ${vo.s1} ${vo.op} ${vo.s2} = ${vo.result} </h2>
	<h2> ${vo.cPage}</h2>
	<hr>
	
	<h2>결과보기</h2>
	<h2> ${vo2.s1} ${vo2.op} ${vo2.s2} = ${vo2.result} </h2>
	<h2> ${vo2.cPage}</h2>
	<h2>
		<c:forEach var="k" items="${vo2.hobby}">
		   <li>${k }</li>
		</c:forEach>
	</h2>
	<hr>
	
</body>
</html>