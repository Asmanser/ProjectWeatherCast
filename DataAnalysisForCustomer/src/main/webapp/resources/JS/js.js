function check() {
    let country = document.getElementById("country").value;
    let date = document.getElementById("date").value;
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (xhr.readyState != 4) return;

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            if(xhr.responseText == "false") {
                alert("Нет погоды на данную дату в данном городу");
                return;
            }
            let weatherInformation = JSON.parse(xhr.responseText);
            console.dir(weatherInformation);
            document.getElementById("cityName").innerText = weatherInformation.city.cityName;
            document.getElementById("temperature").innerText = weatherInformation.minAirTemperature + "..." + weatherInformation.maxAirTemperature;
            document.getElementById("windSpeed").innerText = weatherInformation.windSpeed;
            document.getElementById("directionWind").innerText = weatherInformation.directionWind.direction;
            document.getElementById("atmospherePressure").innerText = weatherInformation.atmospherePressure;
            document.getElementById("airHumidity").innerText = weatherInformation.airHumidity;
            document.getElementById("overcastImg").setAttribute("src",weatherInformation.overcast.image.pathImage + weatherInformation.overcast.image.nameImage);
            document.getElementById("overcast").innerText = weatherInformation.overcast.nameOvercast;
            document.getElementById("weatherConditionImg").setAttribute("src",weatherInformation.weatherCondition.image.pathImage + weatherInformation.weatherCondition.image.nameImage);
            document.getElementById("weatherCondition").innerText = weatherInformation.weatherCondition.nameWeatherConditions;
            document.getElementById("outerWearImg").setAttribute("src",weatherInformation.weatherClothing.outerWear.image.pathImage + weatherInformation.weatherClothing.outerWear.image.nameImage);
            document.getElementById("outerWear").innerText = weatherInformation.weatherClothing.outerWear.nameOuterWear;
            document.getElementById("underWearImg").setAttribute("src",weatherInformation.weatherClothing.underWear.image.pathImage + weatherInformation.weatherClothing.underWear.image.nameImage);
            document.getElementById("underWear").innerText = weatherInformation.weatherClothing.underWear.nameUnderWear;
            document.getElementById("footWearImg").setAttribute("src",weatherInformation.weatherClothing.footWear.image.pathImage + weatherInformation.weatherClothing.footWear.image.nameImage);
            document.getElementById("footWear").innerText = weatherInformation.weatherClothing.footWear.nameFootWear;
            if(typeof weatherInformation.weatherClothing.cap == "undefined") {
                document.getElementById("capImg").removeAttribute("src");
                document.getElementById("cap").innerText = "";
            } else {
                document.getElementById("capImg").setAttribute("src", weatherInformation.weatherClothing.cap.image.pathImage + weatherInformation.weatherClothing.cap.image.nameImage);
                document.getElementById("cap").innerText = weatherInformation.weatherClothing.cap.nameCap;
            }
            if(typeof weatherInformation.weatherClothing.accessory == "undefined") {
                document.getElementById("accessoryImg").removeAttribute("src");
                document.getElementById("accessory").innerText = "";
            } else {
                document.getElementById("accessoryImg").setAttribute("src", weatherInformation.weatherClothing.accessory.image.pathImage + weatherInformation.weatherClothing.accessory.image.nameImage);
                document.getElementById("accessory").innerText = weatherInformation.weatherClothing.accessory.nameAccessory;
            }
        }

    };

    xhr.open('GET', '/index?city=' + country + '&date=' + date, true);
    xhr.send();
}