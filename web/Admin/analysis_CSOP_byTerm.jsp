<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="topSideNav.jsp"/>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/charts_All_CSOP_byTerm.js"></script>
<!-- Add icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>



<div class="main">
    <div id="curve_chart" style="width: 75%; height: 500px; padding-left:300px; padding-top: 150px;" ></div>


    <input type="hidden" id="meanAry" value="${sessionScope.meanList}"/>
    <input type="hidden" id="namesAry" value="${sessionScope.soNames}"/>

    <form id="selections" method="post" action="AdminServlet?action=rawSOPOST">

        <label  for="terms" style="padding-left:650px; padding-top:100px; ">Term:</label>
        <select name="terms" id="terms">
            <c:forEach items="${sessionScope.termList}" var="current">
                <option value="${current.termId}" name="termID">${current.termName} ${current.termYear}</option>
            </c:forEach>
        </select>

        <label for="courses" style="padding-left:10px; padding-top:100px; ">Course:</label>
        <select name="courses" id="courses">
            <c:forEach items="${sessionScope.courseList}" var="current">
                <option value="${current.courseID}" name="courseID"> CoUrSe ${current.courseTitle} </option>
            </c:forEach>
        </select>
        <button type="submit">GO</button>
    </form>
</div>

<script>
/* Loop through all navDropdowns buttons to toggle between hiding and showing its navDropdowns content - This allows the user to have multiple dropdowns without any conflict */
var navDropdowns = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}
</script>

</body>
</html> 