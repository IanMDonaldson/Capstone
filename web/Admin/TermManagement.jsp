<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="topSideNav.jsp"/>
<html>
<head>
<meta charset="UTF-8">
<title>DCIA</title>

    <style>
        .container {
            padding-left: 12vw;
            padding-top: 5vh;
        }
    </style>


</head>
<body>
<div class="container">
<form action="AdminServlet?action=searchActorPOST" method="post">
<label for="termName">Search: </label>
    <input type="text" id="termName" name="termName"/>
    <input type="submit" value="Search Terms" /><br><br>
<a href="AdminServlet?action=getAllTerm">List all Terms</a>
<a href="AdminServlet?action=addTermGET">Add Term</a>
</form>
</div>
</body>
</html>