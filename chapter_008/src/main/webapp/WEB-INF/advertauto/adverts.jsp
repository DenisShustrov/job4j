<%@ page import="java.net.URLEncoder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 07.05.2019
  Time: 6:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Объявления продавца</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h3 align="center">Ваши автомобили</h3>
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
        <th>Статус объявления</th>
        <th>Редактировать</th>
        <th>Продано</th>
        <th>Удалить</th>
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
            <c:choose>
                <c:when test="${advertAuto.saleStatus == false}">
                    <p>Не продана</p>
                </c:when>
                <c:otherwise>
                    <p>Продана</p>
                </c:otherwise>
            </c:choose>
        </td>
        <td>
            <form action='${pageContext.servletContext.contextPath}/update.html' method='get'>
                <input type='hidden' name='id' value='${advertAuto.id}'>
                <button type="submit" class="btn btn-primary" value='update'>Редактировать</button>
            </form>
        </td>
        <td>
            <c:if test="${advertAuto.saleStatus == false}">
                <form action='${pageContext.servletContext.contextPath}/change.html' method='post'>
                    <input type='hidden' name='id' value='${advertAuto.id}'>
                    <button type="submit" class="btn btn-danger" value='delete'>Продано</button>
                </form>
            </c:if>
        </td>
        <td>
            <form action='${pageContext.servletContext.contextPath}/delete.html' method='post'>
                <input type='hidden' name='id' value='${advertAuto.id}'>
                <button type="submit" class="btn btn-danger" value='delete'>Удалить</button>
            </form>
        </td>

    <tr>
        </c:forEach>
    </tbody>
</table>

<table>
    <tr>
        <td>
            <form action='${pageContext.servletContext.contextPath}/add-advert.html' method='get'>
                <button type="submit" class="btn btn-primary" value='crate user' style="margin-right: 10px">
                    Добавить объявление
                </button>
            </form>
        </td>
        <td>
            <form action='${pageContext.servletContext.contextPath}/exit.html' method='get'>
                <button type="submit" class="btn btn-primary" value='exit'>Выход</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
