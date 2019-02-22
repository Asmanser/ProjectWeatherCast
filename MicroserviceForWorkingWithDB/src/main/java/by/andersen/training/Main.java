package by.andersen.training;

import by.andersen.training.config.AppConfig;
import by.andersen.training.models.User;
import by.andersen.training.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //UserRepository userRepository = (UserRepository)context.getBean("UserRepository");
        LocalDate date = LocalDate.now();

        UserService userRepository = context.getBean(UserService.class);
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        userRepository.save(user);
    }

}
