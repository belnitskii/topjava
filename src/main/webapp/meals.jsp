<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1">
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <%--@elvariable id="meals" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"--%>
    <c:forEach items="${meals}" var="meal">
        <%--@elvariable id="meal" type="ru.javawebinar.topjava.model.MealTo"--%>
        <tr>
            <td>${meal.dateTime.toString().replaceAll("T", " ")}</td>
            <td>${meal.description}</td>
            <td style="color: ${meal.excess ? 'red' : 'green'}">${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
