package by.andersen.training;

import by.andersen.training.config.AppConfig;
import by.andersen.training.models.User;
import by.andersen.training.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userRepository = context.getBean(UserService.class);
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        userRepository.save(user);
    }

}
