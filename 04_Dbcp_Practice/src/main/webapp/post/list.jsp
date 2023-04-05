<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	$(function() {
		$('.post').on('click', function() {
			var post_no = $(this).data('post_no'); // this = ul class="post"
			location.href = '${contextPath}/detail.post?post_no=' + post_no;
		})
	}) 
</script>
</head>
<body>

	<a href="${contextPath}/write.post">새 포스트 작성</a>
	
	<hr>
	
	<div class="container">
		<c:forEach var="post" items="${posts}">		
			<ul class="post" data-post_no="${post.post_no}">
				<li>포스트 번호 ${post.post_no}</li>	
				<li>작성자 ${post.writer}</li>
				<li>제목 ${post.title}</li>
				<li>작성일 ${post.created_date}</li>				
			</ul>
		</c:forEach>
	
	
	
	</div>

</body>
</html>