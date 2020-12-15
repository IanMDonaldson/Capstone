<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Department Continuous Improvement Application</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

</head>
<body>
<div class="title_center" style="padding:20px;margin-top:30px;align-content: center;" >
    <h3>The current Term is ${sessionScope.term.termName} ${sessionScope.term.termYear}</h3>
    <h3>Please choose a course you're assigned to.</h3>
    <form action="InstructorServlet?action=changeCoursePOST" method="post">
        <input type="hidden" name="uname" value="${sessionScope.uname}"/>
        <input type="hidden" name="termID" value="${sessionScope.term.termId}"/>
        <label for="select">Course: </label>
        <select id="select" name="courseID">
            <c:forEach items="${sessionScope.courses}" var="current">
                <option value="${current.courseID}">${current.department} ${current.courseNumber} ${current.courseTitle} </option>
            </c:forEach>
        </select>

        <input type="submit" id="submit" name="submit" value="Submit">
    </form>
</div>
</body>
</html>
