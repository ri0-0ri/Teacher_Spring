<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>modifyview</title>
</head>
<body>
	<form name="modifyForm" action="/board/ModifyOk" method="post">
		<fieldset>
			<legend>글쓰기</legend>
				<p>
					제목 <input type="text" name="boardtitle" value="${board.boardtitle}">
				</p>
				<p>
					작성자 <input type="text" name="userid" value="${sessionScope.loginUser}" readonly>
				</p>
				<p>
					작성일자 ${board.regdate}
				</p>
				<p>
					내용 <textarea name="boardcontents">${board.boardcontents}</textarea>
				</p>
				
				<input type="hidden" name="boardnum" value="${board.boardnum}">
				
				<p>
					<input type="submit" value="수정완료">
				</p>				
		</fieldset>
	</form>
</body>
<script>
</script>
</html>