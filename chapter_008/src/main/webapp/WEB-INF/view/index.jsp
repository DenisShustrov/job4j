<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
 Created by IntelliJ IDEA.
 User: denis
 Date: 12.03.2019
 Time: 20:47
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Table with all users</title>
</head>
<body>
<table width='800px' border='1' cellpadding='1' cellspacing='0'>
    <caption>Table with all users</caption>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>createDate</th>
        <th>update</th>
        <th>delete</th>
    </tr>
    <%--@elvariable id="users" type="java.util.List"--%>
    <c:forEach items="${users}" var="user">
    <tr align='center'>
        <td>
            <c:out value="${user.id}">

            </c:out>

        </td>
        <td>
            <c:out value="${user.name}">

            </c:out>
        </td>
        <td>
            <c:out value="${user.login}">

            </c:out>
        </td>
        <td>
            <c:out value="${user.email}">

            </c:out>
        </td>
        <td>
            <c:set var="now" value="${user.createDate}"/>
                <%--<fmt:formatDate type="both" value="${now}"/>--%>
            <c:out value="${now}"/>
        </td>
        <td>
            <form action='${pageContext.servletContext.contextPath}/edit' method='get'>
                <input type='submit' value='update'>
            </form>
        </td>
        <td>
            <form action='${pageContext.servletContext.contextPath}/list' method='post'>
                <input type='hidden' name='id' value='${user.id}'>
                <input type='submit' value='delete'>
            </form>
        </td>
    <tr>
        </c:forEach>
</table>
<br>
<table>
    <tr>
        <td>
            <form action='${pageContext.servletContext.contextPath}/create' method='get'>
                <input type='submit' value='crate user'>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
