package by.andersen.training.services.rabbitmq.implementation;


import by.andersen.training.rmq.AcceptMessageAndSendReplyRMQ;
import by.andersen.training.services.rabbitmq.interfaces.RabbitMQServer;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RabbitMQServerImpl implements RabbitMQServer {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    @Autowired
    private UserParserJsonAndAnswerRMQ userParserJsonAndAnswerRMQ;

    @Autowired
    private CityParserJsonAndAnswerRMQ cityParserJsonAndAnswerRMQ;

    @Autowired
    private OvercastParserJsonAndAnswerRMQ overcastParserJsonAndAnswerRMQ;

    @Autowired
    private DirectionWindParserJsonAndAnswerRMQ directionWindParserJsonAndAnswerRMQ;

    @Autowired
    private WeatherConditionParserJsonAndAnswerRMQ weatherConditionParserJsonAndAnswerRMQ;

    @Autowired
    private WeatherInformationParserJsonAndAnswerRMQ weatherInformationParserJsonAndAnswerRMQ;

    @Autowired
    private AccessoryParserJsonAndAnswerRMQ accessoryParserJsonAndAnswerRMQ;

    @Autowired
    private CapParserJsonAndAnswerRMQ capParserJsonAndAnswerRMQ;

    @Autowired
    private OuterWearParserJsonAndAnswerRMQ outerWearParserJsonAndAnswerRMQ;

    @Autowired
    private FootWearParserJsonAndAnswerRMQ footWearParserJsonAndAnswerRMQ;

    @Autowired
    private UnderWearParserJsonAndAnswerRMQ underWearParserJsonAndAnswerRMQ;

    @PostConstruct
    private void init() {
        connectionFactory.setHost("localhost");
    }

    @Override
    public void open() {
        AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQCity = new AcceptMessageAndSendReplyRMQ(
                connectionFactory,"city",cityParserJsonAndAnswerRMQ);
        acceptMessageAndSendReplyRMQCity.start();

        AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQOvercast = new AcceptMessageAndSendReplyRMQ(
                connectionFactory,"overcast",overcastParserJsonAndAnswerRMQ);
        acceptMessageAndSendReplyRMQOvercast.start();

        AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQDirectionWind = new AcceptMessageAndSendReplyRMQ(
                connectionFactory,"directionWind",directionWindParserJsonAndAnswerRMQ);
        acceptMessageAndSendReplyRMQDirectionWind.start();

        AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQWeatherCondition = new AcceptMessageAndSendReplyRMQ(
                connectionFactory,"weatherCondition",weatherConditionParserJsonAndAnswerRMQ);
        acceptMessageAndSendReplyRMQWeatherCondition.start();

        AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQWeatherInformation = new AcceptMessageAndSendReplyRMQ(
                connectionFactory,"weatherInformation",weatherInformationParserJsonAndAnswerRMQ);
        acceptMessageAndSendReplyRMQWeatherInformation.start();

        AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQAccessory = new AcceptMessageAndSendReplyRMQ(
                connectionFactory,"accessory",accessoryParserJsonAndAnswerRMQ);
        acceptMessageAndSendReplyRMQAccessory.start();

        AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQCap = new AcceptMessageAndSendReplyRMQ(
                connectionFactory,"cap",capParserJsonAndAnswerRMQ);
        acceptMessageAndSendReplyRMQCap.start();

        AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQOuterWear = new AcceptMessageAndSendReplyRMQ(
                connectionFactory,"outerWear",outerWearParserJsonAndAnswerRMQ);
        acceptMessageAndSendReplyRMQOuterWear.start();

        AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQUnderWear = new AcceptMessageAndSendReplyRMQ(
                connectionFactory,"underWear",underWearParserJsonAndAnswerRMQ);
        acceptMessageAndSendReplyRMQUnderWear.start();

        AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQFootWear = new AcceptMessageAndSendReplyRMQ(
                connectionFactory,"footWear",footWearParserJsonAndAnswerRMQ);
        acceptMessageAndSendReplyRMQFootWear.start();
    }

}
