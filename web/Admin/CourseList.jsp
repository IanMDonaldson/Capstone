<%--
  Created by IntelliJ IDEA.
  User: Fordj
  Date: 11/29/2020
  Time: 1:41 AM
  To change this template use File | Settings | File Templates.
--%>
%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="topSideNav.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<label for="courses" style="padding-left:10px; padding-top:100px; ">Course:</label>
<select name="courses" id="courses">
    <c:forEach items="${sessionScope.courseList}" var="current">
        <option value="${current.courseID}"> ${current.courseTitle} ${current.courseNumber} ${current.department} ${current.courseTitle} </option>
    </c:forEach>
</select>
</body>
</html>

