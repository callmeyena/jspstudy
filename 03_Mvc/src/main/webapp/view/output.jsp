<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${today != null}">
		<h2>오늘은 ${today}입니다.</h2>
	</c:if>
	<c:if test="${age != null}">
		<h2>나이는 ${age}살 입니다.</h2>
	</c:if>
	<c:if test="${bmi != null}">
		<h2>BMI ${bmi}는 ${health}입니다. </h2>
	</c:if>
</body>
</html>