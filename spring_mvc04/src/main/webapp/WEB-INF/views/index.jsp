<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function send_go() {
		location.href="gb_list.do";
	}
	function send_go2() {
		location.href="gb2_list.do";
	}
	function bbs_go() {
		location.href = "bbs_list.do";
	}
</script>
</head>
<body>
	<button onclick="send_go()">Guestbook</button>
	<button onclick="send_go2()">Guestbook2</button>
	<button onclick="bbs_go()">게시판</button>
</body>
</html>