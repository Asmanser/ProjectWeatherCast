package by.andersen.training.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "weather_conditions")
public class WeatherCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_weather_conditions", length = 50, nullable = false)
    private String nameWeatherConditions;

    @OneToMany(mappedBy="weatherCondition", fetch = FetchType.LAZY)
    private transient List<WeatherInformation> weatherInformations;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_image")
    private Image image;

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

    public List<WeatherInformation> getWeatherInformations() {
        return weatherInformations;
    }

    public void setWeatherInformations(List<WeatherInformation> weatherInformations) {
        this.weatherInformations = weatherInformations;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
