package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.WeatherClothing;
import by.andersen.training.models.WeatherInformation;
import by.andersen.training.models.rabbitmq.WeatherInformationRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.WeatherInformationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherInformationParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private WeatherInformationService weatherInformationService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        WeatherInformationRMQ weatherInformationRMQ = gson.fromJson(json, WeatherInformationRMQ.class);
        return doOperation(weatherInformationRMQ.getOperation(),weatherInformationRMQ.getWeatherInformation());
    }

    private String doOperation(String operation, WeatherInformation weatherInformation) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                weatherInformationService.save(weatherInformation);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                weatherInformationService.deleteById(weatherInformation.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                weatherInformationService.save(weatherInformation);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<WeatherInformation> weatherInformations = weatherInformationService.findWithAllLazyAll();
                for(WeatherInformation currentWeatherInformation : weatherInformations) {
                    currentWeatherInformation.getCity().setPersonalInformations(null);
                    currentWeatherInformation.getCity().setWeatherInformations(null);
                    currentWeatherInformation.getCity().getCountry().setCities(null);
                    currentWeatherInformation.getOvercast().setWeatherInformations(null);
                    currentWeatherInformation.getWeatherCondition().setWeatherInformations(null);
                    currentWeatherInformation.getDirectionWind().setWeatherInformations(null);
                    WeatherClothing weatherClothing = currentWeatherInformation.getWeatherClothing();
                    weatherClothing.getAccessory().setWeatherClothings(null);
                    weatherClothing.getCap().setWeatherClothingList(null);
                    weatherClothing.getFootWear().setWeatherClothingList(null);
                    weatherClothing.getOuterWear().setWeatherClothings(null);
                    weatherClothing.getOuterWear().setWeatherClothings(null);
                    weatherClothing.setWeatherInformations(null);
                }
                return gson.toJson(weatherInformations);
            }
            case "GETBYID": {
                WeatherInformation findWeatherInformation = weatherInformationService.findById(weatherInformation.getId());
                findWeatherInformation.setCity(null);
                findWeatherInformation.setDirectionWind(null);
                findWeatherInformation.setOvercast(null);
                findWeatherInformation.setWeatherClothing(null);
                findWeatherInformation.setWeatherCondition(null);
                return gson.toJson(findWeatherInformation);
            }
            case "GETBYLAZYID": {
                WeatherInformation findWeatherInformation = weatherInformationService.findWithAllLazyById(weatherInformation.getId());
                findWeatherInformation.getCity().setPersonalInformations(null);
                findWeatherInformation.getCity().setWeatherInformations(null);
                findWeatherInformation.getCity().getCountry().setCities(null);
                findWeatherInformation.getOvercast().setWeatherInformations(null);
                findWeatherInformation.getWeatherCondition().setWeatherInformations(null);
                findWeatherInformation.getDirectionWind().setWeatherInformations(null);
                WeatherClothing weatherClothing = findWeatherInformation.getWeatherClothing();
                weatherClothing.getAccessory().setWeatherClothings(null);
                weatherClothing.getCap().setWeatherClothingList(null);
                weatherClothing.getFootWear().setWeatherClothingList(null);
                weatherClothing.getOuterWear().setWeatherClothings(null);
                weatherClothing.getOuterWear().setWeatherClothings(null);
                weatherClothing.setWeatherInformations(null);
                return gson.toJson(findWeatherInformation);
            }
            case "GETALL": {
                List<WeatherInformation> weatherInformations = weatherInformationService.findAll();
                for(WeatherInformation currentWeatherInformation : weatherInformations) {
                    currentWeatherInformation.setCity(null);
                    currentWeatherInformation.setDirectionWind(null);
                    currentWeatherInformation.setOvercast(null);
                    currentWeatherInformation.setWeatherClothing(null);
                    currentWeatherInformation.setWeatherCondition(null);
                }
                return gson.toJson(weatherInformations);
            }
        }
        return answer;
    }

}
