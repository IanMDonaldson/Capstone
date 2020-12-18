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
    <style>
        .container {
            padding-left: 12vw;
            padding-top: 5vh;
        }
    </style>
</head>
<body>
<div class="container">
<h1><b>${sessionScope.message}</b></h1>
<h3>Students attending ${sessionScope.course.courseTitle} </h3>
<h3> for Term: ${sessionScope.term.termName} ${sessionScope.term.termYear}</h3>
    <div class="grid">
<c:forEach items="${sessionScope.students}" var="current">
    ${current.studentFname} ${current.studentLname}
    <a href="InstructorServlet?action=editStudentGET&studentID=${current.studentId}&courseID=${sessionScope.courseID}&termID=${sessionScope.termID}&uname=${sessionScope.uname}" class="button" id="edit">Edit</a>
    <a href="InstructorServlet?action=deleteStudentGET&studentID=${current.studentId}&courseID=${sessionScope.courseID}&termID=${sessionScope.termID}&uname=${sessionScope.uname}" class="button" id="delete">Delete</a>
    <br>
</c:forEach>
    </div>
</div>
</body>
</html>
