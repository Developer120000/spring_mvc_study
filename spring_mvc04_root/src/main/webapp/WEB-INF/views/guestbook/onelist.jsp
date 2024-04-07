<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<!-- summernote -->
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 800px; margin:auto; text-align: center;}
	
	/* summernote toolbar 수정 */
	.note-btn-group{width: auto;}
	.note-toolbar{width: auto;}
</style>
<script type="text/javascript">
	function delete_go(f) {
		f.action="gb_delete.do";
		f.submit();
	}
	function update_go(f) {
		f.action="gb_update.do";
		f.submit();
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 내용화면</h2>
		<hr>
		<p>[<a href="gb_list.do">목록으로 이동</a>]</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td>${gvo.name }</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제  목</td>
					<td>${gvo.subject }</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td>${gvo.email }</td>
				</tr>
				<tr style="text-align: left;">
					<td colspan="2">
<%-- 						<pre style="padding-left: 15px">${gvo.content}</pre> --%>
						<textarea rows="10" cols="60" id="content" name="content">${gvo.content}</textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
						    <input type="hidden" name="idx" value="${gvo.idx }">
							<input type="button" value="수정" onclick="update_go(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="삭제" onclick="delete_go(this.form)" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" crossorigin="anonymous"></script>
	<script src="resources/js/summernote-lite.js"></script>
	<script src="resources/js/lang/summernote-ko-KR.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#content").summernote({
			    focus:false,
				lang : 'ko-KR',	// 한글 설정
				height : 300,	// 에디터 높이
				focus : true,	// 에디터 로딩후 포커스를 맞출지 여부
				placeholder: '최대3000자까지 쓸 수 있습니다'	,//placeholder 설정
			  
		});
		/* 수정금지 */
		$('#content').summernote('disable');
	});
	</script>
</body>
</html>