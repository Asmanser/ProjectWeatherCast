package by.andersen.training.weathercast.config;

import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig {

    @Bean
    @Scope("singleton")
    public ConnectionFactory connectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        return factory;
    }

    @Bean
    @Scope("prototype")
    public SendMessageAndAcceptResponseRMQ userSendRMQ() {
        SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ = new SendMessageAndAcceptResponseRMQ(
        connectionFactory());
        return sendMessageAndAcceptResponseRMQ;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
