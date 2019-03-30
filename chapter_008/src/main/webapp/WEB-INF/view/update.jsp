<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 12.03.2019
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#country_id').change(function () {
                var country_id = $('#country_id').val();
                if (country_id === '0') {
                    $('#region_id').html('<option>- choose a region -</option>');
                    $('#region_id').attr('disabled', true);
                    $('#city_id').html('<option>- choose a city -</option>');
                    $('#city_id').attr('disabled', true);
                    return (false);
                }
                $('#region_id').attr('disabled', true);
                $('#region_id').html('<option>loading...</option>');
                var url = 'http://localhost:8080/items/region';
                $.get(
                    url,
                    "country_id=" + country_id,
                    function (result) {
                        if (result.type === 'error') {
                            alert('error');
                            return (false);
                        } else {
                            var options = '';
                            $(result).each(function () {
                                options += '<option >' + $(this).attr('name') + '</option>';
                            });
                            $('#region_id').html('<option value="0">- choose a region -</option>' + options);
                            $('#region_id').attr('disabled', false);
                            $('#city_id').html('<option>- choose a city -</option>');
                            $('#city_id').attr('disabled', true);
                        }
                    },
                    "json"
                );
            });

            $('#region_id').change(function () {
                var region_id = $('#region_id :selected').val();
                if (region_id === '0') {
                    $('#city_id').html('<option>- choose a city -</option>');
                    $('#city_id').attr('disabled', true);
                    return (false);
                }
                $('#city_id').attr('disabled', true);
                $('#city_id').html('<option>loading...</option>');
                var url = 'http://localhost:8080/items/cities';
                $.get(
                    url,
                    "region_id=" + region_id,
                    function (result) {
                        if (result.type === 'error') {
                            alert('error');
                            return (false);
                        } else {
                            var options = '';
                            $(result).each(function () {
                                options += '<option >' + $(this).attr('name') + '</option>';
                            });
                            $('#city_id').html('<option>- choose a city -</option>' + options);
                            $('#city_id').attr('disabled', false);
                        }
                    },
                    "json"
                );
            });
        });


        function validate() {
            if ($('#id').val() === "") {
                alert("Please, enter your id!");
                return false;
            }
            return true;
        }

    </script>
</head>
<body>
<h3 style="margin-left: 30px; margin-top:30px">Update users</h3>
    <form action='${pageContext.servletContext.contextPath}/edit' method='post' onsubmit="return validate()">
        <table style="margin-left: 30px; margin-top:30px">
            <tr>
                <td>
                    <div class="form-group">
                        <label for="id">Id:</label>
                        <input type="text" class="form-control" id="id" name="id">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label for="login">Login:</label>
                        <input type="text" class="form-control" id="login" name="login">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" class="form-control" id="email" name="email">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="text" class="form-control" id="password" name="password">
                    </div>
                </td>
            </tr>
            <c:choose>
            <c:when test="${rules_us == 'ADMINISTRATOR'}">
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="sel1">Rules:</label>
                            <select class="form-control" id="sel1" name="rules">
                                <option>Choose a role</option>
                                <c:forEach items="${rules}" var="rul">
                                    <option>${rul}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
            </c:when>
            </c:choose>
            <tr>
                <td>
                    <div class="form-group">
                        <label>Country:</label>
                        <select class="form-control" id="country_id" name="country">
                            <option>- choose a country -</option>
                            <c:forEach items="${country}" var="contry">
                                <option>${contry}</option>
                            </c:forEach>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label>Region:</label>
                        <select class="form-control" id="region_id" name="region">
                            <option>- choose a region -</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label>City:</label>
                        <select class="form-control" id="city_id" name="city">
                            <option>- choose a city -</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox"> Remember me
                        </label>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit" class="btn btn-primary">Update</button>
                </td>
            </tr>
        </table>
    </form>

    <%--<c:when test="${rules_us == 'USER'}">--%>
    <%--<form action='${pageContext.servletContext.contextPath}/edit' method='post'>--%>
    <%--<table style="margin-left: 30px; margin-top:30px">--%>
    <%--<tr>--%>
    <%--<td>--%>
    <%--<div class="form-group">--%>
    <%--<label for="id">Id:</label>--%>
    <%--<input type="text" class="form-control" name="id">--%>
    <%--</div>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>--%>
    <%--<div class="form-group">--%>
    <%--<label for="name">Name:</label>--%>
    <%--<input type="text" class="form-control" name="name">--%>
    <%--</div>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>--%>
    <%--<div class="form-group">--%>
    <%--<label for="login">Login:</label>--%>
    <%--<input type="text" class="form-control" name="login">--%>
    <%--</div>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>--%>
    <%--<div class="form-group">--%>
    <%--<label for="email">Email:</label>--%>
    <%--<input type="text" class="form-control" name="email">--%>
    <%--</div>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>--%>
    <%--<div class="form-group">--%>
    <%--<label for="password">Password:</label>--%>
    <%--<input type="text" class="form-control" name="password">--%>
    <%--</div>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<label for="rules">Rules:</label>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<select class="form-control" id="rules" name="rules">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<option>Choose a role</option>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<c:forEach items="${rules}" var="rul">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<option>${rul}</option>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
    <%--<tr>--%>
    <%--<td>--%>
    <%--<div class="form-group form-check">--%>
    <%--<label class="form-check-label">--%>
    <%--<input class="form-check-input" type="checkbox"> Remember me--%>
    <%--</label>--%>
    <%--</div>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>--%>
    <%--<button type="submit" class="btn btn-primary">Update</button>--%>
    <%--</td>--%>
    <%--</tr>--%>

    <%--</table>--%>
    <%--</form>--%>
    <%--</c:when>--%>

</body>
</html>
