package by.andersen.training.models;

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
@Table(name = "weather_clothing")
public class WeatherClothing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_outerwear")
    private OuterWear outerWear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_underwear")
    private UnderWear underWear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_footwear")
    private FootWear footWear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_caps")
    private Cap cap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_accessories")
    private Accessory accessory;

    @OneToMany(mappedBy="weatherClothing", fetch=FetchType.LAZY)
    private List<WeatherInformation> weatherInformations;

    public WeatherClothing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OuterWear getOuterWear() {
        return outerWear;
    }

    public void setOuterWear(OuterWear outerWear) {
        this.outerWear = outerWear;
    }

    public UnderWear getUnderWear() {
        return underWear;
    }

    public void setUnderWear(UnderWear underWear) {
        this.underWear = underWear;
    }

    public FootWear getFootWear() {
        return footWear;
    }

    public void setFootWear(FootWear footWear) {
        this.footWear = footWear;
    }

    public Cap getCap() {
        return cap;
    }

    public void setCap(Cap cap) {
        this.cap = cap;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }

    public List<WeatherInformation> getWeatherInformations() {
        return weatherInformations;
    }

    public void setWeatherInformations(List<WeatherInformation> weatherInformations) {
        this.weatherInformations = weatherInformations;
    }
}
