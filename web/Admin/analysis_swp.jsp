<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- chart scripts-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/charts_data.js"></script>
    <!-- Add icon library -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <jsp:include page="sidenav.jsp"/>
    <style>
        body {
            font-family: "Lato", sans-serif;
            margin:0;
        }
        .topnav {
            margin: 0 0 0 201px;
            background-color: #F7F8FC;
            position: absolute;
            width: 86%;
        }



        .topnav a  {
            display: block;
            color: #363740;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            float: left;

        }

        .topnav-right {
            float: right;

        }
        .btn {
            background-color:transparent;
            border: none;
            color: #363740;
            padding: 12px 16px;
            font-size: 16px;
            cursor: pointer;

        }
        .btn i {
            padding-right: 4px;
        }
        /*top nav drop down*/
        .dropdown {
            float: left;
            overflow: hidden;
        }

        .dropdown .dropbtn {
            font-size: 16px;
            border: none;
            outline: none;
            color: rgb(0, 0, 0);
            padding: 14px 16px;
            background-color: inherit;
            font-family: inherit;
            margin: 0;
        }



        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            float: none;
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        .dropdown-content a:hover {
            background-color: #ddd;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        /* Fixed sidenav, full height */
        .sidenav {
            height: 100%;
            width: 200px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #363740;
            overflow-x: hidden;
            padding-top: 20px;
        }

        /* Style the sidenav links and the navDropdowns button */
        .sidenav a, .dropdown-btn {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 16px;
            color: #818181;
            display: block;
            border: none;
            background: none;
            width: 100%;
            text-align: left;
            cursor: pointer;
            outline: none;
            display: inline-block;
            word-break: break-word;

        }

        /* On mouse-over */
        .sidenav a:hover, .dropdown-btn:hover {
            color: #f1f1f1;
        }

        /* Main content */
        .main {
            margin-left: 200px; /* Same as the width of the sidenav */

            font-size: 20px; /* Increased text to enable scrolling */
            padding: 100px 10px;
            background-color: #474a69;
        }

        /* Add an active class to the active navDropdowns button */
        .active {
            background-color: rgb(45, 45, 53);
            color: white;
        }

        /* Dropdown container (hidden by default). Optional: add a lighter background color and some left padding to change the design of the navDropdowns content */
        .dropdown-container {
            display: none;
            background-color: #363740;
            padding-left: 6px;

        }

        /* Optional: Style the caret down icon */
        .fa-caret-down {
            float: right;
            padding-right: 8px;
        }

        /* Some media queries for responsiveness */
        @media screen and (max-height: 450px) {
            .sidenav {padding-top: 15px;}
            .sidenav a {font-size: 18px;}
        }

        .chart{

            margin-top: 50px;
        }


    </style>
</head>
<body>
<div class="topnav">

    <a  href="#page_name">Student Work Project</a>
    <div class="topnav-right">
        <button class="btn">Admin</button>
        <button class="btn">Logout</button>
    </div>

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
<div class="main">

    <div class="chart" id="curve_chart" style="width: 75%; height: 500px;  margin-left:100px;" ></div>

    <input type="hidden" id="rawAry" value="${sessionScope.rawList}"/>
    <input type="hidden" id="meanAry" value="${sessionScope.meanList}"/>
    <input type="hidden" id="medianAry" value="${sessionScope.medianList}"/>
    <input type="hidden" id="namesAry" value="${sessionScope.soNames}"/>

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

        <label for="chartFunctions" style="padding-left:10px; padding-top:100px; ">Data:</label>
        <select name="chartFunctions" id="chartFunctions" >
            <option>Select Data</option>
            <option value="Raw">Raw</option>
            <option value="Mean">Mean</option>
            <option value="Median">Median</option>
            <option value="Default">Default</option>

        </select>
        <button id ="submitBtn" type="reset">GO</button>
        <p id="test"  style="padding-top: 100px; padding-left: 300px">This was selected: <spam id="changed"></spam></p>
        <p id="test2"  style="padding-top: 100px; padding-left: 300px">This: <spam id="changed2"></spam></p>

    </form>

</div>


</body>
</html> 