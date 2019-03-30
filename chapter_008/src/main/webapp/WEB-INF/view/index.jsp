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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:choose>
    <c:when test="${rules == 'ADMINISTRATOR'}">
        <div class="container">
            <h3 align="center">Table with all users</h3>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Login</th>
                    <th>Email</th>
                    <th>CreateDate</th>
                    <th>Password</th>
                    <th>Rules</th>
                    <th>Country</th>
                    <th>Region</th>
                    <th>City</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                <tr>
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
                        <c:out value="${user.country}">

                        </c:out>
                    </td>
                    <td>
                        <c:out value="${user.region}">

                        </c:out>
                    </td>
                    <td>
                        <c:out value="${user.city}">

                        </c:out>
                    </td>
                    <td>
                        <form action='${pageContext.servletContext.contextPath}/edit' method='get'>
                            <button type="submit" class="btn btn-primary" value='update'>Update</button>
                        </form>
                    </td>
                    <td>
                        <form action='${pageContext.servletContext.contextPath}/list' method='post'>
                            <input type='hidden' name='id' value='${user.id}'>
                            <button type="submit" class="btn btn-danger" value='delete'>Delete</button>
                        </form>
                    </td>
                <tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <table>
                <tr>
                    <td>
                        <form action='${pageContext.servletContext.contextPath}/create' method='get'>
                            <button type="submit" class="btn btn-primary" value='crate user' style="margin-right: 10px">
                                Crate user
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action='${pageContext.servletContext.contextPath}/exit' method='get'>
                            <button type="submit" class="btn btn-primary" value='exit'>Exit</button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </c:when>
    <c:when test="${rules == 'USER'}">
        <div class="container">
            <h3 align="center">Table with all users</h3>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Login</th>
                    <th>Email</th>
                    <th>CreateDate</th>
                    <th>Password</th>
                    <th>Rules</th>
                    <th>Country</th>
                    <th>Region</th>
                    <th>City</th>
                    <th>Update</th>
                </tr>
                </thead>
                <c:forEach items="${users}" var="user">
                <tr>
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
                        <c:out value="${user.country}">

                        </c:out>
                    </td>
                    <td>
                        <c:out value="${user.region}">

                        </c:out>
                    </td>
                    <td>
                        <c:out value="${user.city}">

                        </c:out>
                    </td>
                    <td>
                        <c:if test="${(login == user.login) and (password == user.password) and (user.rules == 'USER')}">
                            <form action='${pageContext.servletContext.contextPath}/edit' method='get'>
                                <button type="submit" class="btn btn-primary" value='update'>Update</button>
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
                            <button type="submit" class="btn btn-primary" value='exit'>Exit</button>
                        </form>
                    </td>

                </tr>
            </table>
        </div>
    </c:when>
    <c:when test="${rules == 'GUEST'}">
        <div class="container">
            <h3 align="center">Table with all users</h3>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Login</th>
                    <th>Email</th>
                    <th>CreateDate</th>
                    <th>Password</th>
                    <th>Rules</th>
                    <th>Country</th>
                    <th>Region</th>
                    <th>City</th>
                </tr>
                </thead>
                <c:forEach items="${users}" var="user">
                <tr>
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
                        <c:out value="${user.country}">

                        </c:out>
                    </td>
                    <td>
                        <c:out value="${user.region}">

                        </c:out>
                    </td>
                    <td>
                        <c:out value="${user.city}">

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
                            <button type="submit" class="btn btn-primary" value='exit'>Exit</button>
                        </form>
                    </td>

                </tr>
            </table>
        </div>
    </c:when>
</c:choose>
</body>
</html>
