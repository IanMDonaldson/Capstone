
<%--doctype html--%>
<html lang="en">
  <head>
    <title>DCIA</title>
    <!style sheet here>
  </head>
  <body>
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
  </body>
</html>