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
    <title>Car sales</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h3 align="center">${title}</h3>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>Id</th>
        <th>Марка</th>
        <th>Модель</th>
        <th>Год выпуска</th>
        <th>Тип кузова</th>
        <th>Тип двигателя</th>
        <th>Привод</th>
        <th>Коробка передач</th>
        <th>Пробег</th>
        <th>Состояние</th>
        <th>Описание объявления</th>
        <th>Фото</th>
        <th>Цена</th>
        <th>Имя продовца</th>
        <th>Телефон продовца</th>
        <th>Дата размещения</th>
        <th>Статус объявления</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${adverts}" var="advertAuto">
    <tr>
        <td>
            <c:out value="${advertAuto.id}">

            </c:out>

        </td>
        <td>
            <c:out value="${advertAuto.mark}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.model}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.years}">

            </c:out>

        </td>
        <td>
            <c:out value="${advertAuto.bodyType}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.engineType}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.drive}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.transmission}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.mileage}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.condition}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.description}">

            </c:out>
        </td>
        <td>
            <img src="fhotocars/${advertAuto.photoPath}" width="100px">
        </td>
        <td>
            <c:out value="${advertAuto.price}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.nameSeller}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.phoneNumber}">

            </c:out>
        </td>
        <td>
            <c:out value="${advertAuto.createDate}">

            </c:out>
        </td>
        <td>
            <c:choose>
                <c:when test="${advertAuto.saleStatus == false}">
                    <p>Не продана</p>
                </c:when>
                <c:otherwise>
                    <p>Продана</p>
                </c:otherwise>
            </c:choose>
        </td>
    <tr>
        </c:forEach>
    </tbody>
    <table>
        <tr>
            <td>
                <form action='${pageContext.servletContext.contextPath}/adverts.html' method='get'>
                    <button type="submit" class="btn btn-primary" value='exit'>Показать все объявления</button>
                </form>
            </td>
        </tr>
    </table>
</table>
</body>
</html>
