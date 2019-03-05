package by.andersen.training.weathercast.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({@ComponentScan("by.andersen.training.weathercast")})
public class SpringConfig {
}
