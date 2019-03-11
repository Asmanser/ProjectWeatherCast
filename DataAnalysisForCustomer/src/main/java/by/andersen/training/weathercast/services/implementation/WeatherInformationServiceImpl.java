package by.andersen.training.weathercast.services.implementation;

import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.models.User;
import by.andersen.training.weathercast.models.WeatherInformation;
import by.andersen.training.weathercast.models.rabbitmq.WeatherInformationRMQ;
import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
import by.andersen.training.weathercast.services.interfaces.UserService;
import by.andersen.training.weathercast.services.interfaces.WeatherInformationService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WeatherInformationServiceImpl implements WeatherInformationService {

    private String QUEUE_NAME = "weatherInformation";

    private String GET_BY_DATE = "GETBYDATE";
    private String GET_BY_DATE_AND_CITY = "GETBYDATEANDCITY";

    private SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ;

    private Gson gson;

    private UserService userService;

    public WeatherInformationServiceImpl(SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ, Gson gson,
                                         UserService userService) {
        this.sendMessageAndAcceptResponseRMQ = sendMessageAndAcceptResponseRMQ;
        this.gson = gson;
        this.userService = userService;
    }

    @Override
    public WeatherInformation getByDate(Date date, String login) throws InterruptedException {
        sendMessageAndAcceptResponseRMQ.setRPC_Queue_Name(QUEUE_NAME);
        WeatherInformation weatherInformation = new WeatherInformation();
        weatherInformation.setDate(java.sql.Date.valueOf(LocalDate.now()));
        sendMessageAndAcceptResponseRMQ.setMessage(gson.toJson(new WeatherInformationRMQ(GET_BY_DATE,weatherInformation)));
        Thread thread = new Thread(sendMessageAndAcceptResponseRMQ);
        thread.start();
        thread.join();
        Type jsonTypeWeatherInformation = new TypeToken<ArrayList<WeatherInformation>>(){}.getType();
        List<WeatherInformation> weatherInformations = gson.fromJson(sendMessageAndAcceptResponseRMQ.getAnswer(), jsonTypeWeatherInformation);
        User user = userService.getByLogin(login);
        Optional<WeatherInformation> weatherInformationOptional = weatherInformations.stream().filter(x -> x.getCity().getCityName().equals(user.getPersonalInformation().getCity().getCityName())).findFirst();
        if(weatherInformationOptional.isPresent()) {
            return weatherInformationOptional.get();
        }
        return null;
    }

    @Override
    public WeatherInformation getByDateAndCity(java.sql.Date date, int city) throws InterruptedException {
        sendMessageAndAcceptResponseRMQ.setRPC_Queue_Name(QUEUE_NAME);
        WeatherInformation weatherInformation = new WeatherInformation();
        weatherInformation.setDate(date);
        City newCity = new City();
        newCity.setId((long)city);
        weatherInformation.setCity(newCity);
        sendMessageAndAcceptResponseRMQ.setMessage(gson.toJson(new WeatherInformationRMQ(GET_BY_DATE_AND_CITY,weatherInformation)));
        Thread thread = new Thread(sendMessageAndAcceptResponseRMQ);
        thread.start();
        thread.join();
        if(!sendMessageAndAcceptResponseRMQ.getAnswer().equals("false")) {
            WeatherInformation findWeatherInformation = gson.fromJson(sendMessageAndAcceptResponseRMQ.getAnswer(), WeatherInformation.class);
            return findWeatherInformation;
        } else {
            return null;
        }
    }
}
