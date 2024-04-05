<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" crossorigin="anonymous"></script>
<script type="text/javascript">
	// 밑에 글 다 실행하고 나중에 읽힘
	// 처음왔을때는 pwdchk, fail 값이 없어서 실행안함
	// 지금은 jquery 하는데 나중에는 ajax 사용함
	$(document).ready(function() {
		let pwdchk = "${pwdchk}";
		if(pwdchk == 'fail'){
			alert("비밀번호틀림");
			return;
		}
	});
</script>
<script type="text/javascript">
	function update_go(f) {
		f.action="gb_update_ok.do";
		f.submit();
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 수정화면</h2>
		<hr />
		<p>[<a href="gb_list.do">목록으로 이동</a>]</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td><input type="text" name="name" size ="20" value="${gvo.name }"></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제  목</td>
					<td><input type="text" name="subject" size ="20" value="${gvo.subject }"></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td><input type="text" name="email" size ="20" value="${gvo.email }"></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">비밀번호</td>
					<td><input type="password" name="pwd" size ="20" ></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<textarea rows="10" cols="60" id="content" name="content">${gvo.content }</textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="hidden" name="idx" value="${gvo.idx}">
							<input type="button" value="수정완료" onclick="update_go(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="취소" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<script src="resources/js/summernote-lite.js"></script>
	<script src="resources/js/lang/summernote-ko-KR.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#content').summernote({
				lang: "ko-KR",		// 한글 설정
				height: 300,        // 에디터 높이
				focus: true,        // 에디터 로딩후 포커스를 맞출지 여부
				placeholder: '최대3000자까지 쓸 수 있습니다'	//placeholder 설정
			});
		});
		
		function sendImage(file, edfitor) {
			let frm = new FormData(s_file$);	
			frm.append("s_file", file);
			
			$.ajax({
				url : "saverImg.do",
				data : frm,
				type: "post",
				contentType : false,
				processData : false,
				cache : false,
				dataType : "json"
			}).done(function(data) {  /* 성공했을 때랑 같은 뜻 */
				let path = data.path;
				let fname = data.fname;
			$("#content").summernote("editor.insertImage", path + "/" + fname);
			});
		}
	</script>
</body>
</html>