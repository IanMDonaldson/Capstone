
<%--doctype html--%>
<html lang="en">
  <head>
    <title>DCIA</title>
    <!style sheet here>

    <script> type="application/javascript"; src="Script/validateTermAdd.js"> </script>
  </head>
  <body>
  	<h1>Add new Term</h1>
  	<form action="AdminServlet?action=addTermPOST">
		<fieldset>
			<%--@declare id="term year"--%>
                <%--@declare id="term name"--%>
                <label for="Term Year">Term Year: </label><label>
                <input type="number">
            </label><br>
			<label for="Term Name">Term Name: </label><label>
                <input type="text">
            </label><br>
			<input type="submit" name="submit" onclick="" value="Add Term"/>
			<input type="button" onclick="window.location='TermManagement.jsp'" value="Cancel"/>
		</fieldset>
	</form>
  </body>
</html>