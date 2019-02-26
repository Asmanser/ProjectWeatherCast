package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.Cap;
import by.andersen.training.models.rabbitmq.CapRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.CapService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CapParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private CapService capService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        CapRMQ capRMQ = gson.fromJson(json,CapRMQ.class);
        return doOperation(capRMQ.getOperation(),capRMQ.getCap());
    }

    private String doOperation(String operation, Cap cap) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                capService.save(cap);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                capService.deleteById(cap.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                capService.save(cap);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<Cap> caps = capService.findWithAllLazyAll();
                for(Cap currentCap: caps) {
                    currentCap.setWeatherClothingList(null);
                }
                return gson.toJson(caps);
            }
            case "GETBYID": {
                Cap findCap = capService.findById(cap.getId());
                findCap.setImage(null);
                findCap.setWeatherClothingList(null);
                return gson.toJson(findCap);
            }
            case "GETBYLAZYID": {
                Cap findCap = capService.findWithAllLazyById(cap.getId());
                findCap.setWeatherClothingList(null);
                return gson.toJson(findCap);
            }
            case "GETALL": {
                List<Cap> caps = capService.findAll();
                for(Cap currentCap : caps) {
                    currentCap.setImage(null);
                    currentCap.setWeatherClothingList(null);
                }
                return gson.toJson(caps);
            }
        }
        return answer;
    }

}
