package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.Image;
import by.andersen.training.models.rabbitmq.ImageRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.ImageService;
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
