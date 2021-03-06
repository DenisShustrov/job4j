<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 07.05.2019
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Редактировать объявление</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<h3 style="margin-left: 30px; margin-top:30px">Редактировать объявление</h3>
<form action='${pageContext.servletContext.contextPath}/update.html' method='post' enctype='multipart/form-data'>
    <table style="margin-left: 30px; margin-top:30px">
        <tr>
            <td>
                <div class="form-group">
                    <label for="mark">Марка:</label>
                    <input type="text" class="form-control" id="mark" name="mark">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="model">Модель:</label>
                    <input type="text" class="form-control" id="model" name="model">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="years">Год выпуска:</label>
                    <input type="text" class="form-control" id="years" name="years">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="bodyType">Тип кузова:</label>
                    <input type="text" class="form-control" id="bodyType" name="bodyType">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="engineType">Тип двигателя:</label>
                    <input type="text" class="form-control" id="engineType" name="engineType">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="drive">Привод:</label>
                    <input type="text" class="form-control" id="drive" name="drive">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="transmission">Коробка передач:</label>
                    <input type="text" class="form-control" id="transmission" name="transmission">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="mileage">Пробег:</label>
                    <input type="text" class="form-control" id="mileage" name="mileage">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="condition">Состояние:</label>
                    <input type="text" class="form-control" id="condition" name="condition">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="description">Описание объявления:</label>
                    <input type="text" class="form-control" id="description" name="description">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label>Добавить фото:</label>
                    <input type="file" name="file" size="100"/>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="price">Цена:</label>
                    <input type="text" class="form-control" id="price" name="price">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="nameSeller">Имя продовца:</label>
                    <input type="text" class="form-control" id="nameSeller" name="nameSeller">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="phoneNumber">Телефон продовца:</label>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <button type='submit'>Редактировать объявление</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

