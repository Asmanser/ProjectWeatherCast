package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.City;
import by.andersen.training.models.rabbitmq.CityRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.CityService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private CityService cityService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        CityRMQ cityRMQ = gson.fromJson(json,CityRMQ.class);
        return doOperation(cityRMQ.getOperation(),cityRMQ.getCity());
    }

    private String doOperation(String operation, City city) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                cityService.save(city);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                cityService.deleteById(city.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                cityService.save(city);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<City> cities = cityService.findWithAllLazyAll();
                for(City currentCity : cities) {
                    currentCity.setPersonalInformations(null);
                    currentCity.setWeatherInformations(null);
                    currentCity.getCountry().setCities(null);
                }
                return gson.toJson(cities);
            }
            case "GETBYID": {
                City findCity = cityService.findById(city.getId());
                findCity.setPersonalInformations(null);
                findCity.setWeatherInformations(null);
                findCity.setCountry(null);
                return gson.toJson(findCity);
            }
            case "GETBYLAZYID": {
                City findCity = cityService.findWithAllLazyById(city.getId());
                findCity.setPersonalInformations(null);
                findCity.setWeatherInformations(null);
                findCity.getCountry().setCities(null);
                return gson.toJson(findCity);
            }
            case "GETALL": {
                List<City> cities = cityService.findWithAllLazyAll();
                for(City currentCity : cities) {
                    currentCity.setPersonalInformations(null);
                    currentCity.setWeatherInformations(null);
                    currentCity.setCountry(null);
                }
                return gson.toJson(cities);
            }
        }
        return answer;
    }

}
