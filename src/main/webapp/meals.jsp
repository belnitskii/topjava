<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        .excess td {
            color: red;
        }

        .normal td {
            color: green;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
        }

    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="meals?action=insert">Add new</a>
<table>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <%--@elvariable id="meals" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"--%>
    <c:forEach items="${meals}" var="meal">
        <%--@elvariable id="meal" type="ru.javawebinar.topjava.model.MealTo"--%>
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td>${meal.dateTime.toString().replaceAll("T", " ")}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
