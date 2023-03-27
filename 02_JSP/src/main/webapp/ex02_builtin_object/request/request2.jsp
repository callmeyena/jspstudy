<%@page import="java.util.Optional"%>
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
		request.setCharacterEncoding("UTF-8");
		String model = request.getParameter("model");
		Optional opt = Optional.ofNullable(request.getParameter("price")); // ().ofNullable()): price 값이 null일 수도 있고 null이 아닐 수도 있다.
		Object strPrice = opt.orElse("0");	// (.orElse()): 즉, parameter price가 값이 있으면 쓰고 없으면 0이라고 표기한다. 그리고 빈 문자열은 못잡음 optional만 가넝
		int price = Integer.parseInt(strPrice.toString());
	%>
	
	<h1>모델: <%=model%></h1>
	<h1>가격: <%=price%></h1>

</body>
</html>