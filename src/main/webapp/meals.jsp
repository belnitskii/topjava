<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        excess {
            color: blue;
        }

        normal {
            color: green
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="meals?action=insert">Add new</a>
<table border="1">
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <%--@elvariable id="meals" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"--%>
    <c:forEach items="${meals}" var="meal">
        <c:set var="style" value="color: ${meal.excess ? 'red' : 'green'}"/>
        <%--@elvariable id="meal" type="ru.javawebinar.topjava.model.MealTo"--%>
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td style="${style}">${meal.dateTime.toString().replaceAll("T", " ")}</td>
            <td style="${style}">${meal.description}</td>
            <td style="${style}">${meal.calories}</td>
            <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
