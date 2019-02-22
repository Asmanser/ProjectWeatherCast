package by.andersen.training.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weather_clothing")
public class WeatherClothing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OuterWear outerWear;

    private UnderWear underWear;

    private FootWear footWear;

    private Cap cap;

    private Accessory accessory;

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
}
