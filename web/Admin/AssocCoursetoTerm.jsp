<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Film Rental Co.</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script type="application/javascript" src="web/Script/AssocCourseToTerm.js" defer></script>
</head>
<body>
<h2>Associate Actors to ${sessionScope.film.title}</h2>
<form action="AdminServlet?action=assocActorsPOST&id=${sessionScope.termName}" method="post" id="myForm" name="myForm">
    <input type="submit" id="submitButton" value="Submit"><br>
    <input type="hidden" id="hiddenActors" name="hiddenActors" />
    <ul class="prevAssoc">

    </ul>
    <ul class="prevUnassoc">

    </ul>

    <input type="submit" value="Submit" id = "submitButton2">
</form>
</body>