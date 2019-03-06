package by.andersen.training.weathercast.config;

import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
import by.andersen.training.weathercast.services.implementation.CityServiceImpl;
import by.andersen.training.weathercast.services.implementation.RoleServiceImpl;
import by.andersen.training.weathercast.services.implementation.UserServiceImpl;
import by.andersen.training.weathercast.services.interfaces.CityService;
import by.andersen.training.weathercast.services.interfaces.RoleService;
import by.andersen.training.weathercast.services.interfaces.UserService;
import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("by.andersen.training.weathercast")
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
    public SendMessageAndAcceptResponseRMQ sendRMQ() {
        SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ = new SendMessageAndAcceptResponseRMQ(
        connectionFactory());
        return sendMessageAndAcceptResponseRMQ;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public UserService userService() {
        UserServiceImpl userService = new UserServiceImpl(sendRMQ(),gson(), roleService());
        return userService;
    }

    @Bean
    public CityService cityService() {
        CityServiceImpl cityService = new CityServiceImpl(sendRMQ(),gson());
        return cityService;
    }

    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl(sendRMQ(),gson());
    }

}
