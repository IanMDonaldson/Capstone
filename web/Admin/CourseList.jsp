<%--
  Created by IntelliJ IDEA.
  User: Fordj
  Date: 11/29/2020
  Time: 1:41 AM
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
        padding-left: 12vw;
        padding-top: 5vh;
    }
</style>
</head>
<body>
<div class="container">
    <h3>Courses that don't have an instructor assigned to them</h3>
<label for="courses" style="padding-left:10px; padding-top:100px; ">Course:</label>
<select name="courses" id="courses">
    <c:forEach items="${sessionScope.courseList}" var="current">
        <option value="${current.courseID}"> ${current.courseTitle} ${current.courseNumber} ${current.department} ${current.courseTitle} </option>
    </c:forEach>
</select>
</div>
</body>
</html>

