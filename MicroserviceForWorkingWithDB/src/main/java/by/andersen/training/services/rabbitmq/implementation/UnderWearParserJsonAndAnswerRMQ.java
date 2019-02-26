package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.UnderWear;
import by.andersen.training.models.rabbitmq.UnderWearRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.UnderWearService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UnderWearParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private UnderWearService underWear;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        UnderWearRMQ underWearRMQ = gson.fromJson(json, UnderWearRMQ.class);
        return doOperation(underWearRMQ.getOperation(),underWearRMQ.getUnderWear());
    }

    private String doOperation(String operation, UnderWear underWear) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                this.underWear.save(underWear);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                this.underWear.deleteById(underWear.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                this.underWear.save(underWear);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<UnderWear> underWears = this.underWear.findWithAllLazyAll();
                for(UnderWear currentUnderWear : underWears) {
                    currentUnderWear.setWeatherClothings(null);
                }
                return gson.toJson(underWears);
            }
            case "GETBYID": {
                UnderWear findUnderWear = this.underWear.findById(underWear.getId());
                findUnderWear.setImage(null);
                findUnderWear.setWeatherClothings(null);
                return gson.toJson(findUnderWear);
            }
            case "GETBYLAZYID": {
                UnderWear findUnderWear = this.underWear.findWithAllLazyById(underWear.getId());
                findUnderWear.setWeatherClothings(null);
                return gson.toJson(findUnderWear);
            }
            case "GETALL": {
                List<UnderWear> underWears = this.underWear.findAll();
                for(UnderWear currentUnderWear : underWears) {
                    currentUnderWear.setWeatherClothings(null);
                    currentUnderWear.setImage(null);
                }
                return gson.toJson(underWears);
            }
        }
        return answer;
    }

}
