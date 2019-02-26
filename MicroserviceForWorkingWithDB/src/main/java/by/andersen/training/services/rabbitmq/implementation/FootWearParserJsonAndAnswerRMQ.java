package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.FootWear;
import by.andersen.training.models.rabbitmq.FootWearRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.FootWearService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FootWearParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private FootWearService footWearService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        FootWearRMQ footWearRMQ = gson.fromJson(json,FootWearRMQ.class);
        return doOperation(footWearRMQ.getOperation(),footWearRMQ.getFootWear());
    }

    private String doOperation(String operation, FootWear footWear) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                footWearService.save(footWear);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                footWearService.deleteById(footWear.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                footWearService.save(footWear);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<FootWear> footWears = footWearService.findWithAllLazyAll();
                for(FootWear currentFootWear : footWears) {
                    currentFootWear.setWeatherClothingList(null);
                }
                return gson.toJson(footWears);
            }
            case "GETBYID": {
                FootWear findFootWear = footWearService.findById(footWear.getId());
                findFootWear.setWeatherClothingList(null);
                findFootWear.setWeatherClothingList(null);
                return gson.toJson(findFootWear);
            }
            case "GETBYLAZYID": {
                FootWear findFootWear = footWearService.findWithAllLazyById(footWear.getId());
                findFootWear.setWeatherClothingList(null);
                return gson.toJson(findFootWear);
            }
            case "GETALL": {
                List<FootWear> footWears = footWearService.findAll();
                for(FootWear currentFootWear : footWears) {
                    currentFootWear.setWeatherClothingList(null);
                    currentFootWear.setImage(null);
                }
                return gson.toJson(footWears);
            }
        }
        return answer;
    }

}
