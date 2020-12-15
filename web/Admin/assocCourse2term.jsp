<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Title</title>

<style>
    .container {
        padding-left: 12vw;
        padding-top: 5vh;
    }
</style>
<body>
<div class="container">
<h2>Associate Course to ${sessionScope.term.termName} ${sessionScope.term.termYear}</h2>
<form action="AdminServlet?action=assocCourseTermPOST" method="post">
    <input type="hidden" id="hiddenTerms" value="${sessionScope.term.termId}"name="Term">
    <select name = "Cid" id= "Cid" >
    <c:forEach items="${sessionScope.courseList}" var="current">
        <option value="${current.courseID}" >${current.courseNumber} ${current.department} ${current.courseTitle}</option>
    </c:forEach>
    </select>
    <input type="submit" value="Submit" id = "submit">

</form>
</div>
</body>
</html>