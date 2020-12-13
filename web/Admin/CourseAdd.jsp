<%--
  Created by IntelliJ IDEA.
  User: Fordj
  Date: 12/8/2020
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%--doctype html--%>
<%@ include file="topSideNav.jsp" %>
<html lang="en">
<head>
    <title>DCIA</title>
    <!style sheet here>
</head>
<body>
<h1>Add new Course</h1>
<form action="AdminServlet?action=addCoursePOST" method="post">
    <fieldset>
        <label for="courseNumber">Course Number: </label>
        <input type="number"id="courseNumber" name="courseNumber">
        </label><br>
        <label for="courseName">Course Name: </label><label>
        <input type="text"id="courseName" name="courseName">
    </label><br>
        <label for="departmentID">Department ID: </label><label>
        <input type="text"id="departmentID" name="departmentID">
    </label><br>
        <input type="submit" name="submit" onclick="" value="Add Term"/>
    </fieldset>
</form>
</body>
</html>
