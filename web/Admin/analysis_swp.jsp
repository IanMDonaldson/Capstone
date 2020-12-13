<%--
  Created by IntelliJ IDEA.
  User: Fordj
  Date: 12/11/2020
  Time: 5:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/charts_data.js"></script>
    <title>Title</title>
</head>
<body>
<input type="hidden" id="rawAry" value="${sessionScope.rawList}"/>
<input type="hidden" id="meanAry" value="${sessionScope.meanList}"/>
<input type="hidden" id="medianAry" value="${sessionScope.medianList}"/>
<input type="hidden" id="namesAry" value="${sessionScope.soNames}"/>


<div class="chart" id="curve_chart1" style="width: 75%; height: 500px;  margin-left:100px;" ></div>
<div class="chart" id="curve_chart2" style="width: 75%; height: 1000px;  margin-left:100px;" ></div>
<div class="chart" id="curve_chart3" style="width: 75%; height: 1500px;  margin-left:100px;" ></div>
</body>
</html>
