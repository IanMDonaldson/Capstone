<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 11/28/20
  Time: 12:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="topSideNav.jsp"/>
<html>
<head>
    <title>Title</title>
    <style>
        .container {
            padding-top: 5vh;
            padding-left: 12vw;
        }
    </style>
</head>
<body>
<div class="container">
<c:forEach items="${sessionScope.termList}" var="current">
    <h1>${current.termName} ${current.termYear}</h1>
</c:forEach>
</div>
</body>
</html>
