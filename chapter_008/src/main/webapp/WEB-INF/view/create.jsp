<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 12.03.2019
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create users</title>
</head>
<body>

<h3>Create users</h3>

<form action='${pageContext.servletContext.contextPath}/create' method='post'>
    <table>
        <tr>
            <td>id:</td>
            <td><label>
                <input type='text' name='id'>
            </label></td>
        </tr>
        <tr>
            <td>name:</td>
            <td><label>
                <input type='text' name='name'>
            </label></td>
        </tr>
        <tr>
            <td>login:</td>
            <td><label>
                <input type='text' name='login'>
            </label></td>
        </tr>
        <tr>
            <td>email:</td>
            <td><label>
                <input type='text' name='email'>
            </label></td>
        </tr>
        <tr>
            <td>password:</td>
            <td><label>
                <input type='text' name='password'>
            </label></td>
        </tr>
        <tr>
            <td>rules:</td>
            <td>
                <label>
                    <select name="rules">
                        <option>Выберите роль</option>
                        <c:forEach items="${rules}" var="rul">
                            <option>${rul}</option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type='submit'></td>
        </tr>
    </table>
</form>
</body>
</html>
