package by.andersen.training.weathercast.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "direction_wind")
public class DirectionWind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,nullable = false)
    private String direction;

    @OneToMany(mappedBy="directionWind", fetch = FetchType.LAZY)
    private List<WeatherInformation> weatherInformations;

    public DirectionWind() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<WeatherInformation> getWeatherInformations() {
        return weatherInformations;
    }

    public void setWeatherInformations(List<WeatherInformation> weatherInformations) {
        this.weatherInformations = weatherInformations;
    }
}
