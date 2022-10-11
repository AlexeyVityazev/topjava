<%--
  Created by IntelliJ IDEA.
  User: aleksey
  Date: 11.10.2022
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h1>Meals</h1><br/>
<a href="editMeal.jsp">ADD Meal</a>
<table border="1">
    <caption></caption>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="mealTo" items="${requestScope.mealsTo}">
        <tr><td><c:out value="${mealTo.dateTime}"/></td><td><c:out value="${mealTo.description}"/></td><td><c:out value="${mealTo.calories}"/></td></tr>

    </c:forEach>
</table>


</body>
</html>
