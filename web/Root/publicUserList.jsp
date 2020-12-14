<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 12/13/20
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Department Continuous Improvement Application</title>
<style>
    .container {
        display: grid;
        grid-template-columns: 13vw 13vw 13vw 13vw 13vw 13vw 13vw auto;
        grid-gap: 1rem;
    }
</style>
</head>
<body>
<div class="container">
<c:forEach items="${sessionScope.pubUsers}" var="user">
    <div class="email">${user.email}</div>
    <div class="username">${user.username}</div>
    <div class="accessLevel">${user.accessLevel}</div>
    <div class="firstName">${user.firstName}</div>
    <div class="lastName">${user.lastName}</div>
    <a href="RootServlet?action=acceptUserGET&username=${user.username}" class="ui-button" id="accept"><b>Accept</b></a>
    <a href="RootServlet?action=denyUserGET&username=${user.username}" class="ui-button" id="reject"><b>Reject</b></a>
    <br>
</c:forEach>
</div>
</body>
</html>
