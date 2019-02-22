package by.andersen.training.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weather_condition")
public class WeatherCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_weather_conditions", length = 50, nullable = false)
    private String nameWeatherConditions;

    public WeatherCondition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameWeatherConditions() {
        return nameWeatherConditions;
    }

    public void setNameWeatherConditions(String nameWeatherConditions) {
        this.nameWeatherConditions = nameWeatherConditions;
    }
}
