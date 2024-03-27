<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function geustbook_go() {
		location.href = "gb_list.do";
	}
	function geustbook2_go() {
		location.href = "gb2_list.do";
	}
	function bbs_go() {
		location.href = "bbs_list.do";
	}
	function board_go() {
		location.href = "board_list.do";
	}
</script>
</head>
<body>
	<button onclick="geustbook_go()">guestbook</button>
	<button onclick="geustbook2_go()">guestbook2</button>
	<button onclick="bbs_go()">게시판</button>
	<button onclick="board_go()">게시판</button>
</body>
</html>