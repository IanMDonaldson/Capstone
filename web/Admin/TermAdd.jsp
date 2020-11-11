<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<jsp:include page="MenuBar.jsp" />
<%--doctype html--%>
<html lang="en">
  <head>
    <title>DCIA</title>
    <!style sheet here>

    <!script type="application/javascript" src=""></script>
  </head>
  <body>
  	<h1>Add new Term</h1>
  	<%--form action="WebActor?action=addActorPOST&id=${sessionScope.id }" method="post" name="myForm" --%>
  		<input type="hidden" id="id" name="id" value="${sessionScope.id }"/>
		<fieldset>
			<label for="Term Year">Term Year: </label><input type="number"><br>
			<input type="submit" name="submit" onclick="" value="Add Term"/>
			<input type="button" onclick="window.location='ActorManagement.jsp'" value="Cancel"/>
		</fieldset>
	</form>
  </body>
</html>