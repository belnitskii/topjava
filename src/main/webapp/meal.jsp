<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<style>
    table, th, td {
        border: 1px solid black;
    }
</style>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="meal" items="${mealsTo}">
        <tr>
            <c:if test="${meal.excess}">
                <td><p style="color:red;">${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy')}</p></td>
                <td><p style="color:red;">${meal.description}</p></td>
                <td><p style="color:red;">${meal.calories}</p></td>
            </c:if>
            <c:if test="${!meal.excess}">
                <td><p style="color:green;">${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy')}</p></td>
                <td><p style="color:green;">${meal.description}</p></td>
                <td><p style="color:green;">${meal.calories}</p></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>