package by.andersen.training.weathercast.services.rabbitmq.implementation;

import by.andersen.training.weathercast.models.WeatherCondition;
import by.andersen.training.weathercast.models.rabbitmq.WeatherConditionRMQ;
import by.andersen.training.weathercast.rmq.ParserJsonAndAnswerRMQ;
import by.andersen.training.weathercast.services.repository.interfaces.WeatherConditionService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherConditionParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private WeatherConditionService weatherConditionService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        WeatherConditionRMQ weatherConditionRMQ = gson.fromJson(json, WeatherConditionRMQ.class);
        return doOperation(weatherConditionRMQ.getOperation(),weatherConditionRMQ.getWeatherCondition());
    }

    private String doOperation(String operation, WeatherCondition weatherCondition) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                weatherConditionService.save(weatherCondition);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                weatherConditionService.deleteById(weatherCondition.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                weatherConditionService.save(weatherCondition);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<WeatherCondition> weatherConditions = weatherConditionService.findWithAllLazyAll();
                for(WeatherCondition currentWeatherCondition : weatherConditions) {
                    weatherCondition.setWeatherInformations(null);
                }
                return gson.toJson(weatherConditions);
            }
            case "GETBYID": {
                WeatherCondition findWeatherCondition = weatherConditionService.findById(weatherCondition.getId());
                findWeatherCondition.setWeatherInformations(null);
                findWeatherCondition.setImage(null);
                return gson.toJson(findWeatherCondition);
            }
            case "GETBYLAZYID": {
                WeatherCondition findWeatherCondition = weatherConditionService.findWithAllLazyById(weatherCondition.getId());
                findWeatherCondition.setWeatherInformations(null);
                return gson.toJson(findWeatherCondition);
            }
            case "GETALL": {
                List<WeatherCondition> weatherConditions = weatherConditionService.findAll();
                for(WeatherCondition currentWeatherCondition : weatherConditions) {
                    currentWeatherCondition.setWeatherInformations(null);
                    currentWeatherCondition.setImage(null);
                }
                return gson.toJson(weatherConditions);
            }
        }
        return answer;
    }

}
