<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function emp_list_go(f) {
		f.action="emp_list.do";
		f.submit();
	}
	function emp_search_dynamic(f) {
		f.action="emp_search.do";
		f.submit();
	}
</script>
</head>
<body>
	<h2>동적쿼리페이지</h2>
	<form method="post">
		<p><input type="button" value="전체보기(20)" onclick="emp_list_go(this.form)"></p>
		<p>
			<select name="idx">
				<option>:: 선택하세요 ::</option>
				<option value="1">사번</option>
				<option value="2">이름</option>
				<option value="3">성별</option>
				<option value="4">생년월일</option>
			</select>
			<input type="text" name="keyword">
			<input type="button" value="동적검색" onclick="emp_search_dynamic(this.form)">
		</p>
	</form>
</body>
</html>