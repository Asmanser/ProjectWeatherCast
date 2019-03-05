package by.andersen.training.weathercast.services.rabbitmq.implementation;

import by.andersen.training.weathercast.models.OuterWear;
import by.andersen.training.weathercast.models.rabbitmq.OuterWearRMQ;
import by.andersen.training.weathercast.rmq.ParserJsonAndAnswerRMQ;
import by.andersen.training.weathercast.services.repository.interfaces.OuterWearService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OuterWearParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private OuterWearService outerWearService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        OuterWearRMQ outerWearRMQ = gson.fromJson(json,OuterWearRMQ.class);
        return doOperation(outerWearRMQ.getOperation(),outerWearRMQ.getOuterWear());
    }

    private String doOperation(String operation, OuterWear outerWear) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                outerWearService.save(outerWear);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                outerWearService.deleteById(outerWear.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                outerWearService.save(outerWear);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<OuterWear> outerWears = outerWearService.findWithAllLazyAll();
                for(OuterWear currentOuterWear : outerWears) {
                    currentOuterWear.setWeatherClothings(null);
                }
                return gson.toJson(outerWears);
            }
            case "GETBYID": {
                OuterWear findOuterWear = outerWearService.findById(outerWear.getId());
                findOuterWear.setImage(null);
                findOuterWear.setWeatherClothings(null);
                return gson.toJson(findOuterWear);
            }
            case "GETBYLAZYID": {
                OuterWear findOuterWear = outerWearService.findWithAllLazyById(outerWear.getId());
                findOuterWear.setWeatherClothings(null);
                return gson.toJson(findOuterWear);
            }
            case "GETALL": {
                List<OuterWear> outerWears = outerWearService.findAll();
                for(OuterWear currentOuterWear : outerWears) {
                    currentOuterWear.setImage(null);
                    currentOuterWear.setWeatherClothings(null);
                }
                return gson.toJson(outerWears);
            }
        }
        return answer;
    }

}
