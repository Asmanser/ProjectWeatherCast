package by.andersen.training.weathercast.services.rabbitmq.implementation;

import by.andersen.training.weathercast.models.Overcast;
import by.andersen.training.weathercast.models.rabbitmq.OvercastRMQ;
import by.andersen.training.weathercast.rmq.ParserJsonAndAnswerRMQ;
import by.andersen.training.weathercast.services.repository.interfaces.OvercastService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OvercastParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private OvercastService overcastService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        OvercastRMQ overcastRMQ = gson.fromJson(json,OvercastRMQ.class);
        return doOperation(overcastRMQ.getOperation(),overcastRMQ.getOvercast());
    }

    private String doOperation(String operation, Overcast overcast) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                overcastService.save(overcast);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                overcastService.deleteById(overcast.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                overcastService.save(overcast);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<Overcast> overcasts = overcastService.findWithAllLazyAll();
                for(Overcast currentOvercast : overcasts) {
                    currentOvercast.setWeatherInformations(null);
                }
                return gson.toJson(overcasts);
            }
            case "GETBYID": {
                Overcast findOvercast = overcastService.findById(overcast.getId());
                findOvercast.setImage(null);
                findOvercast.setWeatherInformations(null);
                return gson.toJson(findOvercast);
            }
            case "GETBYLAZYID": {
                Overcast findOvercast = overcastService.findWithAllLazyById(overcast.getId());
                findOvercast.setWeatherInformations(null);
                return gson.toJson(findOvercast);
            }
            case "GETALL": {
                List<Overcast> overcasts = overcastService.findAll();
                for(Overcast currentOvercast : overcasts) {
                    currentOvercast.setWeatherInformations(null);
                    currentOvercast.setImage(null);
                }
                return gson.toJson(overcasts);
            }
        }
        return answer;
    }

}
