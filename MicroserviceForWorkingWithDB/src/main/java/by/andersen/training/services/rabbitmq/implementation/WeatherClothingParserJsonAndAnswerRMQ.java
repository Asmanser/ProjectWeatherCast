package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.WeatherClothing;
import by.andersen.training.models.rabbitmq.WeatherClothingRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.WeatherClothingService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WeatherClothingParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private WeatherClothingService weatherClothingService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        WeatherClothingRMQ weatherClothingRMQ = gson.fromJson(json, WeatherClothingRMQ.class);
        return doOperation(weatherClothingRMQ.getOperation(),weatherClothingRMQ.getWeatherClothing());
    }

    private String doOperation(String operation, WeatherClothing weatherClothing) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                weatherClothingService.save(weatherClothing);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                weatherClothingService.deleteById(weatherClothing.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                weatherClothingService.save(weatherClothing);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<WeatherClothing> weatherClothings = weatherClothingService.findWithAllLazyAll();
                for(WeatherClothing currentWeatherCloting : weatherClothings) {
                    currentWeatherCloting.getAccessory().setWeatherClothings(null);
                    currentWeatherCloting.getCap().setWeatherClothingList(null);
                    currentWeatherCloting.getFootWear().setWeatherClothingList(null);
                    currentWeatherCloting.getOuterWear().setWeatherClothings(null);
                    currentWeatherCloting.getOuterWear().setWeatherClothings(null);
                    currentWeatherCloting.setWeatherInformations(null);
                }
                return gson.toJson(weatherClothings);
            }
            case "GETBYID": {
                WeatherClothing findWeatherClothing = weatherClothingService.findById(weatherClothing.getId());
                findWeatherClothing.setWeatherInformations(null);
                findWeatherClothing.setCap(null);
                findWeatherClothing.setFootWear(null);
                findWeatherClothing.setUnderWear(null);
                findWeatherClothing.setAccessory(null);
                findWeatherClothing.setOuterWear(null);
                return gson.toJson(findWeatherClothing);
            }
            case "GETBYLAZYID": {
                WeatherClothing findWeatherClothing = weatherClothingService.findWithAllLazyById(weatherClothing.getId());
                findWeatherClothing.getAccessory().setWeatherClothings(null);
                findWeatherClothing.getCap().setWeatherClothingList(null);
                findWeatherClothing.getFootWear().setWeatherClothingList(null);
                findWeatherClothing.getOuterWear().setWeatherClothings(null);
                findWeatherClothing.getOuterWear().setWeatherClothings(null);
                findWeatherClothing.setWeatherInformations(null);
                return gson.toJson(findWeatherClothing);
            }
            case "GETALL": {
                List<WeatherClothing> weatherClothings = weatherClothingService.findAll();
                for(WeatherClothing currentWeatherCloting : weatherClothings) {
                    currentWeatherCloting.setWeatherInformations(null);
                    currentWeatherCloting.setCap(null);
                    currentWeatherCloting.setFootWear(null);
                    currentWeatherCloting.setUnderWear(null);
                    currentWeatherCloting.setAccessory(null);
                    currentWeatherCloting.setOuterWear(null);
                }
                return gson.toJson(weatherClothings);
            }
        }
        return answer;
    }

}
