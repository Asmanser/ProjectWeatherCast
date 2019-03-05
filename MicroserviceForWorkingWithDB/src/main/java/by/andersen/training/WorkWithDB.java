package by.andersen.training;

import by.andersen.training.weathercast.config.AppConfig;
import by.andersen.training.weathercast.services.rabbitmq.interfaces.RabbitMQServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class WorkWithDB {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        RabbitMQServer bean = context.getBean(RabbitMQServer.class);
        bean.open();
    }

}
