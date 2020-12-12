<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 11/29/20
  Time: 7:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Department Continuous Improvement Application</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #363740;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #363740;
            color: white;
        }
        h1 {
            text-align: center;
        }



        .topnav-right {
            float: right;
        }
    </style>
</head>
<body>
<div class="topnav">
    <div class="topnav-right">
        <button class="btn">Logout</button>
    </div>
</div>
<input type="hidden" id="raw" value="${sessionScope.rawList}">

<!--todo finish this shit-->
<div class="title_center" style="padding:20px;margin-top:30px;align-content: center;" >
    <h3>The current Term is ${sessionScope.term.termName} ${sessionScope.term.termYear}</h3>
    <h3>Please choose a course you're assigned to.</h3>
    <form action="InstructorServlet?action=changeCoursePOST" method="post">
        <input type="hidden" name="termID" value="${sessionScope.term.termId}"/>
        <c:forEach items="${sessionScope.courses}" var="current">
            <option name="courseID" value="${current.courseID}"><b>${current.department} ${current.courseNumber}</b> ${current.courseTitle}</option>
        </c:forEach>
        <input type="submit" id="submit" name="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
