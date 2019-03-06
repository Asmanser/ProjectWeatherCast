package by.andersen.training.weathercast.services.implementation;

import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.models.rabbitmq.CityRMQ;
import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
import by.andersen.training.weathercast.services.interfaces.CityService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CityServiceImpl implements CityService {

    private String QUEUE_NAME = "city";

    private String GET_ALL_WITH_LAZY = "GETALLWITHLAZY";

    private SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ;

    private Gson gson;

    public CityServiceImpl(SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ, Gson gson) {
        this.sendMessageAndAcceptResponseRMQ = sendMessageAndAcceptResponseRMQ;
        this.gson = gson;
    }

    @Override
    public List<City> getAllCity() throws InterruptedException {
        List<City> cities = new ArrayList<>();
        sendMessageAndAcceptResponseRMQ.setRPC_Queue_Name(QUEUE_NAME);
        sendMessageAndAcceptResponseRMQ.setMessage(gson.toJson(new CityRMQ(GET_ALL_WITH_LAZY,new City())));
        Thread thread1 = new Thread(sendMessageAndAcceptResponseRMQ);
        thread1.start();
        thread1.join();
        Type jsonTypeCities = new TypeToken<ArrayList<City>>(){}.getType();
        cities = gson.fromJson(sendMessageAndAcceptResponseRMQ.getAnswer(), jsonTypeCities);
        return cities;
    }
}
