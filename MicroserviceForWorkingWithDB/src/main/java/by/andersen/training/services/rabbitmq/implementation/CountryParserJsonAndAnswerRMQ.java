package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.City;
import by.andersen.training.models.Country;
import by.andersen.training.models.rabbitmq.CountryRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.CountryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private CountryService countryService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        CountryRMQ countryRMQ = gson.fromJson(json,CountryRMQ.class);
        return doOperation(countryRMQ.getOperation(),countryRMQ.getCountry());
    }

    private String doOperation(String operation, Country country) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                countryService.save(country);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                countryService.deleteById(country.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                countryService.save(country);
                answer = "true";
                return answer;
            }
            case "GETBYID": {
                Country findCountry = countryService.findById(country.getId());
                findCountry.setCities(null);
                return gson.toJson(findCountry);
            }
            case "GETALL": {
                List<Country> countries = countryService.findAll();
                for(Country currentCountry : countries) {
                    currentCountry.setCities(null);
                }
                return gson.toJson(countries);
            }
        }
        return answer;
    }

}
