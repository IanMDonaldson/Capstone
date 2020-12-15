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
</div>
</body>
</html>
