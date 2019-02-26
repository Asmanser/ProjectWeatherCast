package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.Accessory;
import by.andersen.training.models.rabbitmq.AccessoryRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.AccessoryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccessoryParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private AccessoryService accessoryService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        AccessoryRMQ accessoryRMQ = gson.fromJson(json,AccessoryRMQ.class);
        return doOperation(accessoryRMQ.getOperation(),accessoryRMQ.getAccessory());
    }

    private String doOperation(String operation, Accessory accessory) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                accessoryService.save(accessory);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                accessoryService.deleteById(accessory.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                accessoryService.save(accessory);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<Accessory> accessoryList = accessoryService.findWithAllLazyAll();
                for(Accessory currentAccessory : accessoryList) {
                    currentAccessory.setWeatherClothings(null);
                }
                return gson.toJson(accessoryList);
            }
            case "GETBYID": {
                Accessory findAccessory = accessoryService.findById(accessory.getId());
                findAccessory.setImage(null);
                findAccessory.setWeatherClothings(null);
                return gson.toJson(findAccessory);
            }
            case "GETBYLAZYID": {
                Accessory findAccessory = accessoryService.findWithAllLazyById(accessory.getId());
                findAccessory.setWeatherClothings(null);
                return gson.toJson(findAccessory);
            }
            case "GETALL": {
                List<Accessory> accessoryList = accessoryService.findAll();
                for(Accessory currentAccessory : accessoryList) {
                    currentAccessory.setWeatherClothings(null);
                    accessory.setImage(null);
                }
                return gson.toJson(accessoryList);
            }
        }
        return answer;
    }

}
