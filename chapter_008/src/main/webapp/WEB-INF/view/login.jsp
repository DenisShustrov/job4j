<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 17.03.2019
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Authorization form</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background: lime">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action='${pageContext.servletContext.contextPath}/login' method='post'>
    <table width='300px'>
        <caption>Authorization form</caption>
        <tr>
            <td><b>login</b></td>
        </tr>
        <tr>
            <td>
                <label>
                    <input type='text' name='login'>
                </label>
            </td>
        </tr>
        <tr>
            <td><b>Password</b></td>
        </tr>
        <tr>
            <td><label>
                <input type='text' name='password'>
            </label></td>
        </tr>
        <tr>
            <td><input type='submit'>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
