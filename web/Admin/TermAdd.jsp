<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="topSideNav.jsp"/>
<html lang="en">
  <head>
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
  	<h1>Add new Term</h1>
  	<form action="AdminServlet?action=addTermPOST" method="post">
		<fieldset>
			<%--@declare id="term year"--%>
                <%--@declare id="term name"--%>
                <
                <label for="termYear">Term Year: </label>
                <input type="number"id="termYear" name="termYear">
            </label><br>
			<label for="termName">Term Name: </label><label>
                <input type="text"id="termName" name="termName">
            </label><br>
			<input type="submit" name="submit" onclick="" value="Add Term"/>
		</fieldset>
	</form>
  </div>
  </body>
</html>