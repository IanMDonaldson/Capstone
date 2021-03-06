<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="topSideNav.jsp"/>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/charts_csop.js"></script>
<!-- Add icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>
<div class="main">

    <div class="chart" id="curve_chart" style="width: 75%; height: 500px;  margin-left:100px;" ></div>
    <input type="hidden" id="meanAry" value="${sessionScope.meanList}"/>
    <input type="hidden" id="namesAry" value="${sessionScope.soNames}"/>
    <input type="hidden" id="instructorsAry" value="${sessionScope.instructorsNames}"/>

    <form style="background-color: red" id="selections" method="post" action="AdminServlet?action=rawSOPOST">

        <label  for="terms" style="padding-left:300px; padding-top:100px; ">Term:</label>
        <select name="terms" id="terms">
            <c:forEach items="${sessionScope.termList}" var="current">
                <option value="${current.termId}" name="termID">${current.termName} ${current.termYear}</option>
            </c:forEach>
        </select>

        <label for="courses" style="padding-left:10px; padding-top:100px; ">Course:</label>
        <select name="courses" id="courses" style="width:auto;">
            <c:forEach items="${sessionScope.courseList}" var="current">
                <option value="${current.courseID}" name="courseID"> CoUrSe ${current.courseTitle} </option>
            </c:forEach>
        </select>

        <label for="instructors" style="padding-left:10px; padding-top:100px; ">Data:</label>
        <select name="instructors" id="instructors">
            <c:forEach items="${sessionScope.instructorList}" var="current">
                <option value="${current.instructorID}" name="courseID">${current.instructorName} </option>
            </c:forEach>
        </select>
        <button id ="submitBtn" type="submit">GO</button>
    </form>
</div>


<div class="main">
  
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