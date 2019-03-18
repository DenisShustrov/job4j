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

<c:choose>
    <c:when test="${rules == 'ADMINISTRATOR'}">
        <table width='800px' border='1' cellpadding='1' cellspacing='0'>
            <caption>Table with all users</caption>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>login</th>
                <th>email</th>
                <th>createDate</th>
                <th>password</th>
                <th>rules</th>
                <th>update</th>
                <th>delete</th>
            </tr>
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
                    <c:out value="${now}"/>
                </td>
                <td>
                    <c:out value="${user.password}">

                    </c:out>
                </td>
                <td>
                    <c:out value="${user.rules}">

                    </c:out>
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
                <td>
                    <form action='${pageContext.servletContext.contextPath}/exit' method='get'>
                        <input type='submit' value='exit'>
                    </form>
                </td>
            </tr>
        </table>
    </c:when>

    <c:when test="${rules == 'USER'}">
        <table width='800px' border='1' cellpadding='1' cellspacing='0'>
            <caption>Table with all users</caption>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>login</th>
                <th>email</th>
                <th>createDate</th>
                <th>password</th>
                <th>rules</th>
                <th>update</th>
            </tr>
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
                    <c:out value="${now}"/>
                </td>
                <td>
                    <c:out value="${user.password}">

                    </c:out>
                </td>
                <td>
                    <c:out value="${user.rules}">

                    </c:out>
                </td>
                <td>
                    <c:if test="${(login == user.login) and (password == user.password) and (user.rules == 'USER')}">
                        <form action='${pageContext.servletContext.contextPath}/edit' method='get'>
                            <input type='submit' value='update'>
                        </form>
                    </c:if>
                </td>
            <tr>
                </c:forEach>
        </table>
        <br>
        <table>
            <tr>
                <td>
                    <form action='${pageContext.servletContext.contextPath}/exit' method='get'>
                        <input type='submit' value='exit'>
                    </form>
                </td>

            </tr>
        </table>
    </c:when>
    <c:when test="${rules == 'GUEST'}">
        <table width='800px' border='1' cellpadding='1' cellspacing='0'>
            <caption>Table with all users</caption>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>login</th>
                <th>email</th>
                <th>createDate</th>
                <th>password</th>
                <th>rules</th>
            </tr>
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
                    <c:out value="${now}"/>
                </td>
                <td>
                    <c:out value="${user.password}">

                    </c:out>
                </td>
                <td>
                    <c:out value="${user.rules}">

                    </c:out>
                </td>
            <tr>
                </c:forEach>
        </table>
        <br>
        <table>
            <tr>
                <td>
                    <form action='${pageContext.servletContext.contextPath}/exit' method='get'>
                        <input type='submit' value='exit'>
                    </form>
                </td>

            </tr>
        </table>
    </c:when>
</c:choose>
</body>
</html>
