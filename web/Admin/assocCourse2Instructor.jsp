<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Title</title>

<body>
<h2>Associate Course to</h2>
<form action="AdminServlet?action=assocInstructorPOST" method="post">
    <input type="hidden" id="hiddenTerms" value="${sessionScope.term.termId}"name="Term">
    <select name = "Cid" id= "Cid" >
        <c:forEach items="${sessionScope.courseList}" var="current">
            <option value="${current.courseID}" >${current.courseNumber} ${current.department} ${current.courseTitle}</option>
        </c:forEach>
    </select>

    <select name = "InsID" id= "InsID" >
        <c:forEach items="${sessionScope.instructorList}" var="current">
            <option value="${current.username}" >${current.firstName} ${current.lastName}</option>
        </c:forEach>
    </select>

</label>
    <input type="submit" value="Submit" id = "submit">

</form>
</body>
</html>