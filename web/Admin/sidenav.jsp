<%--
  Created by IntelliJ IDEA.
  User: dragomundo
  Date: 11/27/20
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="sidenav">
    <a id="noClick">Fall 2019</a>
    <button class="dropdown-btn">Analysis
        <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-container">
        <a href="../AdminServlet?action=analyzeRawGET">SWP-Row, Mean, & Median Scores</a>
        <a href="analysis_CSOP.jsp">Course SO Performance</a>
        <a href="analysis_ISOP.jsp">Instructor SO Performance</a>
        <a href="analysis_ALL_CSOP_byTerm.jsp">All Courses SO Performance by Term</a>
        <a href="analysis_CSOP_byTerm.jsp">Courses SO Performance by Term</a>
    </div>
    <a href="Setup_Term_Year.jsp">Setup Terms & Years</a>
    <a href="instuctors.jsp">Instructor</a>


</div>
</body>
</html>
