<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/CSS/style.css">
</head>

<body>

<div class="login-form">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h3 class="form-signin-heading">Create your account</h3>
        <spring:bind path="login">
            <div class="form-group">
                <form:input type="text" path="login" class="form-control" placeholder="Login"
                            autofocus="true" required="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group">
                <form:input type="password" path="password" class="form-control" placeholder="Password" required="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div class="form-group">
                <form:input type="password" path="confirmPassword" class="form-control"
                            placeholder="Confirm your password" required="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="personalInformation.name">
            <div class="form-group">
                <form:input type="text" path="personalInformation.name" class="form-control"
                            placeholder="Input your name" required="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="personalInformation.surname">
            <div class="form-group">
                <form:input type="text" path="personalInformation.surname" class="form-control"
                            placeholder="Input your surname" required="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="personalInformation.patronymic">
            <div class="form-group">
                <form:input type="text" path="personalInformation.patronymic" class="form-control"
                            placeholder="Input your patronymic" required="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="personalInformation.age">
            <div class="form-group">
                <form:input type="number" path="personalInformation.age" class="form-control"
                            placeholder="Input your age" required="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="personalInformation.email">
            <div class="form-group">
                <form:input type="text" path="personalInformation.email" class="form-control"
                            placeholder="Input your email" pattern="^(([^<>()\[\]\\.,;:\s@\"]+(\.[^<>()\[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="personalInformation.city.id">
            <div class="form-group">
                <form:select path="personalInformation.city.id" class="form-control">
                    <c:forEach var="city" items="${cities}">
                        <form:option value="${city.id}">
                            ${city.cityName}
                        </form:option>
                    </c:forEach>
                </form:select>
            </div>
        </spring:bind>


        <span style="color:red;">${error}</span>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>