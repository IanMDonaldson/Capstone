<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DCIA</title>



</head>
<body>
<form action="AdminServlet?action=searchActorPOST" method="post">
<label for="termName">Search: </label>
    <input type="text" id="termName" name="termName"/>
    <input type="submit" value="Search Terms" /><br><br>
<a href="AdminServlet?action=getAllTerm">List all Terms</a>
<a href="AdminServlet?action=addTermGET">Add Term</a>
</form>
</body>
</html>