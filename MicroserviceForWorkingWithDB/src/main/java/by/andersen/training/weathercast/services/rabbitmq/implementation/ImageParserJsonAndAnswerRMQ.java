package by.andersen.training.weathercast.services.rabbitmq.implementation;

import by.andersen.training.weathercast.models.Image;
import by.andersen.training.weathercast.models.rabbitmq.ImageRMQ;
import by.andersen.training.weathercast.rmq.ParserJsonAndAnswerRMQ;
import by.andersen.training.weathercast.services.repository.interfaces.ImageService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private ImageService imageService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        ImageRMQ imageRMQ = gson.fromJson(json, ImageRMQ.class);
        return doOperation(imageRMQ.getOperation(),imageRMQ.getImage());
    }

    private String doOperation(String operation, Image image) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                imageService.save(image);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                imageService.deleteById(image.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                imageService.save(image);
                answer = "true";
                return answer;
            }
            case "GETBYID": {
                Image findImage = imageService.findById(image.getId());
                return gson.toJson(findImage);
            }
            case "GETALL": {
                List<Image> images = imageService.findAll();
                return gson.toJson(images);
            }
        }
        return answer;
    }

}
