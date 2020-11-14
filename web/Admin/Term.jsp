<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="MenuBar.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Rental Co. - List of Actors!</title>
<link rel="stylesheet" href="css/MenuBar.css"/>

<link rel="stylesheet" href="css/Actor.css"/>

</head>
<body>
<a href="javascript:history.back()" class="button">Return to Term List</a>
<a href="WebActor?action=updateActorGET&id=${sessionScope.id }" class="button">Update Term</a>
<a href="WebActor?action=deleteActorGET&id=${sessionScope.id }" class="button">Delete Term</a><br><br>
	<div class="types">Term Year:  <input type="text" id="termYear" name="termName"/>
	<div class="types">Term Name:  <input type="text" id="termName" name="termName"/>
	<div class="types">Term's ID:  <input type="text" id="termID" name="termName"/>
	</div>

</body>
</html>