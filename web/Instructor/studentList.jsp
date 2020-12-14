<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 11/29/20
  Time: 6:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="topSideNav.jsp"%>
<html>
<head>
    <title>Department Continuous Improvement Application</title>

</head>
<body>
<h1><b>${sessionScope.message}</b></h1>
<h3>Students attending ${sessionScope.course.courseId} ${sessionScope.course.courseTitle} </h3>
<h3> for Term: ${sessionScope.term.termName} ${sessionScope.term.termYear}</h3>
<c:forEach items="${sessionScope.studentList}" var="current">
    ${current.studentFname} ${current.studentLname}
    <a href="InstructorServlet?action=editStudent&id=${current.studentId}&courseID=${sessionScope.course.courseId}
        &termID=${sessionScope.term.termId}&uname=${sessionScope.uname}" class="button" id="edit">Edit</a>
    <a href="InstructorServlet?action=deleteStudent&id=${current.studentId}" class="button" id="delete">Delete</a>
    <br>
</c:forEach>
</body>
</html>
