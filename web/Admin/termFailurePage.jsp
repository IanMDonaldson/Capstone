<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Movie Rental Co. - Failure Page</title>
<meta charset="UTF-8">
</head>
<body>

<c:choose>
	<c:when test="${sessionScope.add }">
		<h2>Add failed!</h2><br>
		<a href="Home">Click Here to return to Main Menu</a>
	</c:when>
	<c:when test="${sessionScope.update }">
		<h2>Update Failed!</h2><br><br>
		<a href="Home">Click Here to return to Main Menu</a>
	</c:when>
	<c:when test="${sessionScope.delete }">
		<h2>Deletion Failed!</h2><br><br>
		<a href="Home">Click Here to return to Main Menu</a>
	</c:when>
</c:choose>

</body>
</html>