<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 05.05.2019
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Выбор марки автомобиля</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/filter.html'>
    <table style="margin-left: 30px; margin-top:30px">
        <tr>
            <td>
                <div class="form-group">
                    <label for="mark">Выберете марку автомобиля:</label>
                    <input type="text" class="form-control" id="mark" name="mark">
                    <input type="hidden" name="filter" value="показать определенной марки">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" class="btn btn-primary">Выбрать</button>
            </td>
        </tr>
    </table>
</body>
</html>
