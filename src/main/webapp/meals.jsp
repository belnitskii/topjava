<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.util.List" %>
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
    <%
        List<MealTo> meals = (List<MealTo>) request.getAttribute("meals");
        for (MealTo meal : meals) {
    %>
    <tr>
        <td><%=meal.getDateTime().toString().replaceAll("T", " ")%></td>
        <td><%=meal.getDescription()%></td>
        <%if (meal.isExcess()) { %>
            <td><span style="color: red"><%=meal.getCalories()%></span></td>
        <%} else {%>
            <td><span style="color: green"><%=meal.getCalories()%></span></td>
        <%}%>
    </tr>
    <%}%>
</table>
</body>
</html>
