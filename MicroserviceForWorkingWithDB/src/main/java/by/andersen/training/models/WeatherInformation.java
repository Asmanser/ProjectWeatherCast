package by.andersen.training.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "weather_informations")
public class WeatherInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_air_temperature",nullable = false)
    private int minAirTemperature;

    @Column(name = "max_air_temperature",nullable = false)
    private int maxAirTemperature;

    @Column(name = "wind_speed", nullable = false)
    private int windSpeed;

    @Column(name = "atmosphere_pressure", nullable = false)
    private int atmospherePressure;

    @Column(name = "air_humidity", nullable = false)
    private int airHumidity;

    @Column(name = "date", nullable = false)
    private Date date;

    private City city;

    private DirectionWind directionWind;

    private Overcast overcast;

    private WeatherCondition weatherCondition;

    private WeatherClothing weatherClothing;

    public WeatherInformation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinAirTemperature() {
        return minAirTemperature;
    }

    public void setMinAirTemperature(int minAirTemperature) {
        this.minAirTemperature = minAirTemperature;
    }

    public int getMaxAirTemperature() {
        return maxAirTemperature;
    }

    public void setMaxAirTemperature(int maxAirTemperature) {
        this.maxAirTemperature = maxAirTemperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getAtmospherePressure() {
        return atmospherePressure;
    }

    public void setAtmospherePressure(int atmospherePressure) {
        this.atmospherePressure = atmospherePressure;
    }

    public int getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(int airHumidity) {
        this.airHumidity = airHumidity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public DirectionWind getDirectionWind() {
        return directionWind;
    }

    public void setDirectionWind(DirectionWind directionWind) {
        this.directionWind = directionWind;
    }

    public Overcast getOvercast() {
        return overcast;
    }

    public void setOvercast(Overcast overcast) {
        this.overcast = overcast;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public WeatherClothing getWeatherClothing() {
        return weatherClothing;
    }

    public void setWeatherClothing(WeatherClothing weatherClothing) {
        this.weatherClothing = weatherClothing;
    }
}
