<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="topSideNav.jsp"/>
<head>
    <title>DCIA</title>
    <style>
        .container {
            padding-left: 12vw;
            padding-top: 5vh;
        }
    </style>
</head>
<body>
<h1>Add new Course</h1>
<div class="container">
<form action="AdminServlet?action=addCoursePOST" method="post">
    <fieldset>
        <label for="courseNumber">Course Number: </label>
        <input type="number"id="courseNumber" name="courseNumber" required>
        <br>
        <label for="courseName">Course Name: </label>
        <input type="text"id="courseName" name="courseName" required>
    <br>
        <label for="departmentID">Department ID: </label>
        <input type="text" id="departmentID" name="departmentID" pattern="[A-Z]{0,4}$" required>
    <br>
        <input type="submit" name="submit" onclick="" value="Add Term"/>
    </fieldset>
</form>
</div>
</body>
</html>
