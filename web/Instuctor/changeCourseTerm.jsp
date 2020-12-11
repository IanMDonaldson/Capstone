<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 11/29/20
  Time: 7:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="topSideNav.jsp" %>
<html>
<head>
    <title>Department Continuous Improvement Application</title>

</head>
<body>
    <c:forEach>
        <option name="Cid" value="${}"></option>
    </c:forEach>
</body>
</html>
