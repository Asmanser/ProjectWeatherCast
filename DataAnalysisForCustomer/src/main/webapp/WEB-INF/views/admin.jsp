<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 06.03.2019
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <a href="/" class="navbar-brand">
        <img src="">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a href="/" class="nav-link">Home</a>
            </li>
        </ul>
        <form id="logoutForm" method="POST" action="${contextPath}/logout" class="form-inline my-2 my-lg-0">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-outline-danger my-2 my-sm-0">Log Out</button>
        </form>
    </div>
</nav>
<div class="container">
    <table class="table table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">Login</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Patronymic</th>
            <th scope="col">Age</th>
            <th scope="col">Email</th>
            <th scope="col">City</th>
            <th scope="col">Admin</th>
            <th scope="col">Delete</th>
        </tr>
        <c:forEach>

        </c:forEach>
        </thead>
    </table>
</div>
</body>
</html>
