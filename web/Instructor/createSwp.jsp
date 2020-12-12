<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 12/9/20
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="topSideNav.jsp" %>
<html>
<head>
    <title>Department Continuous Improvement Application</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/createSwp.css"/>
</head>
<body>
<h3>Create a new Student Work Product by filling in the required datafields and selecting one or more Student Outcomes to associate them to.</h3>
<form action="InstructorServlet?action=createSwpPOST" method="post">
    <input type="hidden" name="termID" value="${sessionScope.termID}"/>
    <input type="hidden" name="courseID" value="${sessionScope.courseID}"/>
    <input type="hidden" name="uname" value="${sessionScope.uname}"/>
    <div class="container">
        <div class="swpInput">
            <label for="swpTitle">Name of Student Work Product: </label>
            <input type="text" id="swpTitle" name="swpTitle">
        </div>
        <div class="studentOutcomes">
            <c:forEach items="${sessionScope.studentOutcomes}" var="so">
                <div class="soCheckbox">
                    <input type="checkbox" id="soID" name="soID" value="${so.soID}">
                    <label for="soID" id="soLabel"><b>${so.soID}.</b> ${so.title}</label>
                </div>
            </c:forEach>
        </div>
    </div>
    <input type="submit" name="submit" id="submit" value="Save">
</form>


<script>
    /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
    var dropdown = document.getElementsByClassName("dropdown-btn");
    var i;
    for (i = 0; i < dropdown.length; i++) {
        dropdown[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var dropdownContent = this.nextElementSibling;
            if (dropdownContent.style.display === "block") {
                dropdownContent.style.display = "none";
            } else {
                dropdownContent.style.display = "block";
            }
        });
    }
</script>

</body>
</html>
