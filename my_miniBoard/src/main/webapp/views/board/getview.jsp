<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>getview</title>
</head>

<c:if test="${cookie.w.value == 'mt'}">
	<!--수정 완료하고 돌아왔다-->
	<script>
		alert("게시글 수정 완료!");
	</script>
</c:if>

<body>
	<p>
		제목 <input type="text" name="boardtitle" value="${board.boardtitle}" readonly>
	</p>
	<p>
		작성자 <input type="text" name="userid" value="${sessionScope.loginUser}" readonly>
	</p>
	<p>
		작성일자 ${board.regdate}
	</p>
	<p>
		내용 <textarea name="boardcontents" readonly>${board.boardcontents}</textarea>
	</p>
	<hr>
	<div id="btn_area">
		<a href="/user/main">목록</a>
		<c:if test="${board.userid == loginUser}">
			<!--수정 버튼 클릭 시 수정 페이지로 이동, 그 곳에서 수정 완료 클릭하면 다시 getview로 돌아오기-->
			<a href="/board/modifyview?boardnum=${board.boardnum}">수정</a>
			<!--삭제 버튼 클릭 시 게시글 삭제 후 메인 페이지로 이동-->
			<a href="/board/DeleteOk?boardnum=${board.boardnum}">삭제</a>
		</c:if>
	</div>
</body>
<script>
</script>
</html>