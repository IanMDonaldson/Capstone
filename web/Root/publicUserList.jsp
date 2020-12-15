<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 12/13/20
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Department Continuous Improvement Application</title>
<style>
    .container {
        display: grid;
        grid-template-columns: 13vw 13vw 13vw 13vw 13vw 13vw 13vw auto;
        grid-gap: 1rem;
    }
    h3{
        position: absolute;
        float: top;
        padding-top: 5vh;
        padding-left: 40vw;
        padding-right: 40vw;
    }
     body {margin:0;
         font-family: Arial, Helvetica, sans-serif
     }

    /* Navagation/header styles */
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
    h1{
        color:#363740;
        text-align: center;

    }
    .title_cennter{

        margin:auto;
    }

    /* style the container */
    .container {
        position: relative;
        border-radius: 5px;
        padding: 20px 0 30px 0;
    }
    form{

        margin-left: 20%;
        margin-right:20%;
    }

    /* style inputs and link buttons */
    input, select {
        width: 100%;
        padding: 12px;
        border: none;
        border-radius: 4px;
        margin: 5px 0;
        opacity: 0.85;
        display: inline-block;
        font-size: 17px;
        line-height: 20px;
        text-decoration: none; /* remove underline from anchors */
    }
    select{
        width: 105%;
    }
    .registation_btn{
        width: 105%;
    }
    input:hover,
    .btn:hover {
        opacity: 1;
    }




    /* Two-column layout */
    .col {
        float: start;
        width: 50%;
        margin: auto;
        padding: 0 50px;
        margin-top: 6px;
    }
    .rol{
        display: block;
    }
    .rol input{
        width: 50%;
    }
    h1 .title_pg{
        text-align:center
    }
    /* hide some text on medium and large screens */
    .hide-md-lg {
        display: none;
    }
    /* hide the vertical line */
    .vl {
        display: none;
    }
    /* show the hidden text on small screens */
    .hide-md-lg {
        display: block;
        text-align: center;
    }


</style>
</head>
<body>
<div class="topnav">
    <a class="active" href="${pageContext.request.contextPath}/home_page.jsp">Home</a>
    <div class="topnav-right">
        <a href="LoginServlet?action=loginGET">Login</a>
        <a href="Register?action=registerGET">Register</a>
    </div>
</div>
<h3>Pending Registration Requests</h3>
<div class="container">
<c:forEach items="${sessionScope.pubUsers}" var="user">
    <div class="email">${user.email}</div>
    <div class="username">${user.username}</div>
    <div class="accessLevel">${user.accessLevel}</div>
    <div class="firstName">${user.firstName}</div>

    <div class="lastName">${user.lastName}</div>
    <a href="RootServlet?action=acceptUserGET&username=${user.username}" class="ui-button" id="accept"><b>Accept</b></a>
    <a href="RootServlet?action=denyUserGET&username=${user.username}" class="ui-button" id="reject"><b>Reject</b></a>
    <br>
</c:forEach>
</div>
</body>
</html>
