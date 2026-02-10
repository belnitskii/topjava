<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>${meal == null ? "Add meal" : "Edit meal"}</h2>
<body>
<form method="POST" action="meals" name="mealServlet">

    <table border="1">
        <tr>
            <th>DateTime</th>
            <td><input type="datetime-local" value="${meal.dateTime}" name="datetime"/></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><input type="text" value="${meal.description}" name="description"/></td>
        </tr>
        <tr>
            <th>Calories</th>
            <td><input type="number" value="${meal.calories}" name="calories"/></td>
        </tr>
    </table>

    <input type="hidden" value="${meal.id}" name="id"/>
    <input type="reset" value="Reset">
    <input type="submit" value="Submit">
    <a href="meals"><button type="button">Back</button></a>
</form>
</body>
</html>
