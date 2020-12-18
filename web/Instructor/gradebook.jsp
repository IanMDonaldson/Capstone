<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="topSideNav.jsp" %>
<html>
<head>
    <title>Department Continuous Improvement Application</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gradebook.css">
</head>
<body>
<div class="formContainer">
<form action="InstructorServlet?action=gradebookPOST" method="post">
    <input type="hidden" name="termID" value="${sessionScope.termID}">
    <input type="hidden" name="courseID" value="${sessionScope.courseID}">
    <input type="hidden" name="uname" value="${sessionScope.uname}">
    <div class="tableContainer">
        <table class="table">
            <thead>
            <tr>
                <th>Students</th>
                <c:forEach items="${sessionScope.swpNames}" var="name">
                    <th><b>${name}</b></th>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.students}" var="student">
                <tr>
                    <td>
                        <b>${student.studentLname}, ${student.studentFname}</b>
                    </td>
                    <c:forEach items="${student.swpList}" var="swp">
                        <td><input type="text" id="grade" name="swp${swp.swpID}" value="${swp.grade}"></td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <input type="submit" id="submit" name="submit"  value="Save Changes">
</form>
</div>
</body>
</html> 