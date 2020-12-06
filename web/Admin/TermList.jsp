<%--
  Created by IntelliJ IDEA.
  User: Fordj
  Date: 11/28/2020
  Time: 12:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<label  for="terms" style="padding-left:650px; padding-top:100px; ">Term:</label>
<select name="terms" id="terms">
    <c:forEach items="${sessionScope.termList}" var="current">
        <option value="${current.termId}">${current.termName} ${current.termYear}</option>
    </c:forEach>
</select>
</body>
</html>

