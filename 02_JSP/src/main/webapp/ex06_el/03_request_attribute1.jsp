<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// request에 속성 a 라는 이름으로 숫자 1을 저장
		request.setAttribute("a", 1); 
	
		// request를 전달하는 forward
		request.getRequestDispatcher("03_request_attribute2.jsp").forward(request, response);	
	%>
	
	<%--
		참고. forward를 수행하는 Jsp 액션 태그
		<jsp: forward page="03_request_attribute2.jsp"></jsp:forward>
	 --%>

</body>
</html>