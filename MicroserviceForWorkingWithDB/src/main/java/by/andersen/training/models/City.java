package by.andersen.training.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name",length = 200, nullable = false)
    private String cityName;

    @OneToMany(mappedBy="city", fetch=FetchType.LAZY)
    private List<PersonalInformation> personalInformations;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_country")
    private Country country;

    @OneToMany (mappedBy="city", fetch = FetchType.LAZY)
    private List<WeatherInformation> weatherInformations;

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<PersonalInformation> getPersonalInformations() {
        return personalInformations;
    }

    public void setPersonalInformations(List<PersonalInformation> personalInformations) {
        this.personalInformations = personalInformations;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<WeatherInformation> getWeatherInformations() {
        return weatherInformations;
    }

    public void setWeatherInformations(List<WeatherInformation> weatherInformations) {
        this.weatherInformations = weatherInformations;
    }
}
