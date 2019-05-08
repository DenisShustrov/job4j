<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 07.05.2019
  Time: 5:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация пользователя</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background: lime">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action='${pageContext.servletContext.contextPath}/login.html' method='post'>
    <table style="margin-left: 30px; margin-top:30px">
        <tr>
            <td>
                <div class="form-group">
                    <label for="login">Логин:</label>
                    <input type="text" class="form-control" id="login" name="login">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="pwd">Пароль:</label>
                    <input type="text" class="form-control" id="pwd" name="password">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" class="btn btn-primary">Вход</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
