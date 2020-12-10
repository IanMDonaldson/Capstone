<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 11/28/20
  Time: 12:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${sessionScope.termList}" var="current">
    <h1>${current.termName}</h1>
</c:forEach>
</body>
</html>
