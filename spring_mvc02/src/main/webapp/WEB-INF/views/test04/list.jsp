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
	<h1>회원 정보 목록</h1>
	<table>
		<thead>
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>NAME</th>
			<th>EMAIL</th>
			<th>ADDR</th>
			<th>PHONE</th>
			<th>REGDATE</th>
			<th>DELETE</th>
		</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list}">
					<tr><td colspan="8"><h3>원하는 정보가 존재하지 않습니다.</h3></td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${list}" varStatus="vs">
						<tr>
							<td>${vs.count}</td>
							<td>${k.id}</td>
							<td>${k.name}</td>
							<td>${k.email}</td>
							<td>${k.addr}</td>
							<td>${k.phone}</td>
							<td>${k.regdate.substring(0, 10)}</td>
							<td><input type="button" value="삭제" onclick="delete_go(${k.idx})"></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>