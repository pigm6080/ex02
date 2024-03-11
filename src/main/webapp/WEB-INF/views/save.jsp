<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>save</title>
</head>
<body>
	<form action="/board/save" method="post">
		<input type="text" name="boardWriter" placeholder="작성자" /><br /><br />
		<input type="text" name="boardPass" placeholder="비밀번호" /><br /><br />
		<input type="text" name="boardTitle" placeholder="제목" /><br /><br />
		
		<textarea name = "boardContents" rows="10" cols="30" placeholder="내용을 입력하세요"></textarea><br><br>
		
		<input type="submit" value="작성"/>
	</form>
</body>
</html>