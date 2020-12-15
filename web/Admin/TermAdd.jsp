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
                <label for="termYear">Term Year: </label>
                <input type="text" id="termYear" name="termYear" pattern="^[0-9]*$">
            <br>
			<label for="termName">Term Name: </label>
                <input type="text"id="termName" name="termName">
           <br>
			<input type="submit" name="submit" value="Add Term"/>
		</fieldset>
	</form>
  </div>
  </body>
</html>