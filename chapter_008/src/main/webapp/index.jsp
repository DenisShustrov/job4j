<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.crud.ValidateService" %><%--
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
<table width='600px' border='1' cellpadding='1' cellspacing='0'>
    <caption>Table with all users</caption>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>update</th>
        <th>delete</th>
    </tr>
    <%for (User us : ValidateService.getInstance().find()) {%>
    <tr align='center'>
        <td>
            <%=us.getId()%>
        </td>
        <td>
            <%=us.getName()%>
        </td>
        <td>
            <%=us.getLogin()%>
        </td>
        <td>
            <%=us.getEmail()%>
        </td>
        <td>
            <form action='<%=request.getContextPath()%>/update.jsp' method='get'>
                <input type='submit' value='update'>
            </form>
        </td>
        <td>
            <form action='<%=request.getContextPath()%>/list' method='post'>
                <input type='hidden' name='id' value='<%=us.getId()%>'>
                <input type='submit' value='delete'>
            </form>
        </td>
    <tr>
            <%}%>
</table>
<br>
<table>
    <tr>
        <td>
            <form action='<%=request.getContextPath()%>/create.jsp' method='get'>
                <input type='submit' value='crate user'>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
