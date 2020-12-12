<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 11/29/20
  Time: 7:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="topSideNav.jsp" %>
<html>
<head>
    <title>Department Continuous Improvement Application</title>
   <style>
        .container {
            display: grid;
            grid-template-columns: 15vw 15vw 15vw auto;
            grid-gap: 1rem;
        }
        .div {
            width: 15vw;
            height: 75vh;
            vertical-align: center;
        }

        #studentDiv {
            overflow-y: scroll;
            overflow-x: hidden;
            height: 80vh;
            flex-direction: column;
        }
        #studentListDiv {
            height: 2vh;
        }


        h1 {
            padding-top: 4%;
            padding-left: 20%;
            float:top;
            overflow-wrap: normal;
        }




   </style>
</head>
<body>
<h1><b>Please select all students that are taking your Course to enroll them into your roster.</b></h1>
<form action="InstructorServlet?action=assocStudentsPOST" method="post">
    <input type="hidden" name="termID"  value="${sessionScope.termID}"  />
    <input type="hidden" name="uname" value="${sessionScope.uname}"/>
    <div class="container">
        <div id="studentDiv" class="div">
            <c:forEach items="${sessionScope.students}" var="student">
                <div id="studentListDiv">
                    <input type="checkbox" id="studentbox"name="student" value="${student.studentId}">

                    <label for="studentbox" id="studentlabel" class="studentlabel">${student.studentLname}, ${student.studentFname}</label>
                </div>
                <br>
            </c:forEach>
        </div>
        <div id="courseDiv" class="div">
            <label for="courseSelect"></label>
            <select id="courseSelect" name="courseSelect">
                <c:forEach items="${sessionScope.courses}" var="course">
                    <option id="courses" name="courseID" value="${course.courseID}"><b>${course.department} ${course.courseNumber}</b> - ${course.courseTitle}</option>
                </c:forEach>
            </select>
        </div>
        <div class="div" id="submitDiv">
            <input id="submit" name="submit" type="submit" value="Enroll Students to Course"/>
        </div>
    </div>
</form>
</body>
</html>
