<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 11/30/20
  Time: 12:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="topSideNav.jsp" %>
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
<form action="InstructorServlet?action=editStudentPOST" method="post">
    <input type="hidden" name="termID" value="${sessionScope.termID}">
    <input type="hidden" name="courseID" value="${sessionScope.courseID}">
    <input type="hidden" name="uname" value="${sessionScope.uname}">
    <input type="hidden" name="studentID" value="${sessionScope.student.studentId}">
    <label for="studentFname">First Name: </label>
    <input type="text" name="studentFname" id="studentFname" value="${sessionScope.student.studentFname}">
    <br>
    <label for="studentLname">Last Name: </label>
    <input type="text" name="studentLname" id="studentLname" value="${sessionScope.student.studentLname}">
    <br>
    <input type="submit" name="submit" value="Save Changes">
</form>
</div>
</body>
</html>
