<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DCIA</title>



</head>
<body>
<form action="WebActor?action=searchActorPOST" method="post">
<label for="actorName">Search: </label>
    <input type="text" id="actorName" name="actorName"/>
    <input type="submit" value="Search Actors" /><br><br>
<a href="WebActor?action=getAllActors">List all Terms</a>
<a href="WebActor?action=addActorGET">Add Actor</a>
</form>
</body>
</html>