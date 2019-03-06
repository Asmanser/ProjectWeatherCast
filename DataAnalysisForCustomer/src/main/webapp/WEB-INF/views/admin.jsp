<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.login}</td>
                <td>${user.personalInformation.name}</td>
                <td>${user.personalInformation.surname}</td>
                <td>${user.personalInformation.patronymic}</td>
                <td>${user.personalInformation.age}</td>
                <td>${user.personalInformation.email}</td>
                <td>${user.personalInformation.city.cityName}</td>
                <td>
                <c:set var="flag" scope="request" value="false"></c:set>
                <c:forEach var="role" items="${user.roles}">
                    <c:if test="${role.roleName.equals('ROLE_ADMIN')}">
                        <c:set var="flag" scope="request" value="true"></c:set>
                        <form action="/admin/role" method="post">
                            <input type="hidden" name="login" value="${user.login}">
                            <input type="hidden" name="_method" value="delete">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="submit" class="btn btn-danger" value="Delete Role Admin">
                        </form>
                    </c:if>
                </c:forEach>
                <c:if test="${flag == false}">
                    <form action="/admin/role" method="post">
                        <input type="hidden" name="login" value="${user.login}">
                        <input type="hidden" name="_method" value="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" class="btn btn-success" value="Add Role Admin">
                    </form>
                </c:if>
                </td>
                <td>
                    <form action="/admin/user" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="_method" value="delete">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" class="btn btn-danger" value="Delete User">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </thead>
    </table>
</div>
</body>
</html>
