<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>
    <script src="/resources/JS/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<security:authentication var="user" property="principal" />
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
            <security:authorize access="hasAnyRole('ADMIN')">
                <li class="nav-item">
                    <a href="/admin" class="nav-link">ADMIN</a>
                </li>
            </security:authorize>
        </ul>
        <form id="logoutForm" method="POST" action="${contextPath}/logout" class="form-inline my-2 my-lg-0">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-outline-danger my-2 my-sm-0">Log Out</button>
        </form>
    </div>
</nav>
<div class="container" style="margin-top: 10px;">
    <div class="row">
        <form class="form-inline">
            <div class="form-group mb-2 mr-sm-2">
                <label for="country">Town: </label>
                <select class="form-control" id="country">
                    <c:forEach var="city" items="${cities}">
                        <c:if test="${city.id == perinfo.city.id}">
                            <option value="${city.id}" selected>${city.cityName}</option>
                        </c:if>
                        <c:if test="${city.id != perinfo.city.id}">
                            <option value="${city.id}">${city.cityName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group mb-2 mr-sm-2">
                <label for="date">Date: </label>
                <input id="date" type="date" class="form-control" value="${weatherInformation.date}">
            </div>
            <div class="form-group mb-2 mr-sm-2">
                <input type="button" class="btn btn-primary" onclick="check()" value="Check">
            </div>
        </form>
    </div>
    <div class="row" style="margin-bottom: 25px;">
        <div class="col-4">
            <h3 id="textCity">
                Weather for the city <pre id="cityName">${weatherInformation.city.cityName}</pre>
            </h3>
            <p>Temperature: <pre id="temperature">${weatherInformation.minAirTemperature} ... ${weatherInformation.maxAirTemperature}</pre></p>
            <p>Wind speed: <pre id="windSpeed">${weatherInformation.windSpeed}</pre></p>
            <p>Direction of the wind: <pre id="directionWind">${weatherInformation.directionWind.direction}</pre></p>
            <p>Atmosphere pressure: <pre id="atmospherePressure">${weatherInformation.atmospherePressure}</pre></p>
            <p>Air humidity: <pre id="airHumidity">${weatherInformation.airHumidity}</pre></p>
        </div>
        <div class="col-4">
            <h4>Overcast</h4>
            <img id="overcastImg" height="75%" width="100%" src="${weatherInformation.overcast.image.pathImage}${weatherInformation.overcast.image.nameImage}">
            <h5 id="overcast">${weatherInformation.overcast.nameOvercast}</h5>
        </div>
        <div class="col-4">
            <h4>Weather Condition</h4>
            <img id="weatherConditionImg" height="75%" width="100%" src="${weatherInformation.weatherCondition.image.pathImage}${weatherInformation.weatherCondition.image.nameImage}">
            <h5 id="weatherCondition">${weatherInformation.weatherCondition.nameWeatherConditions}</h5>
        </div>
    </div>
    <div class="row">
        <div class="col-2">
            <h4>Outerwear</h4>
            <img id="outerWearImg" height="100%" width="100%" src="${weatherInformation.weatherClothing.outerWear.image.pathImage}${weatherInformation.weatherClothing.outerWear.image.nameImage}">
            <h5 id="outerWear">${weatherInformation.weatherClothing.outerWear.nameOuterWear}</h5>
        </div>
        <div class="col-2">
            <h4>Underwear</h4>
            <img id="underWearImg" height="100%" width="100%" src="${weatherInformation.weatherClothing.underWear.image.pathImage}${weatherInformation.weatherClothing.underWear.image.nameImage}">
            <h5 id="underWear">${weatherInformation.weatherClothing.underWear.nameUnderWear}</h5>
        </div>
        <div class="col-2">
            <h4>FootWear</h4>
            <img id="footWearImg" height="100%" width="100%" src="${weatherInformation.weatherClothing.footWear.image.pathImage}${weatherInformation.weatherClothing.footWear.image.nameImage}">
            <h5 id="footWear">${weatherInformation.weatherClothing.footWear.nameFootWear}</h5>
        </div>
        <div class="col-2">
            <h4>Cap</h4>
            <c:if test="${weatherInformation.weatherClothing.cap != null}">
                <img id="capImg" height="100%" width="100%" src="${weatherInformation.weatherClothing.cap.image.pathImage}${weatherInformation.weatherClothing.cap.image.nameImage}">
                <h5 id="cap">${weatherInformation.weatherClothing.cap.nameCap}</h5>
            </c:if>
            <c:if test="${weatherInformation.weatherClothing.cap == null}">
                <img id="capImg" height="100%" width="100%">
                <h5 id="cap"></h5>
            </c:if>
        </div>
        <div class="col-2">
            <h4>Accessory</h4>
            <c:if test="${weatherInformation.weatherClothing.accessory != null}">
                <img id="accessoryImg" height="100%" width="100%" src="${weatherInformation.weatherClothing.accessory.image.pathImage}${weatherInformation.weatherClothing.accessory.image.nameImage}">
                <h5 id="accessory">${weatherInformation.weatherClothing.accessory.nameAccessory}</h5>
            </c:if>
            <c:if test="${weatherInformation.weatherClothing.accessory == null}">
                <img id="accessoryImg" height="100%" width="100%">
                <h5 id="accessory"></h5>
            </c:if>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="/resources/JS/js.js"></script>
</body>
</html>