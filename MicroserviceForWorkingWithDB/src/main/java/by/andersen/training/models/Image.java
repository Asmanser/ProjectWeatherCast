package by.andersen.training.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_image", length = 255, nullable = false)
    private String nameImage;

    @Column(name = "path_image", length = 500, nullable = false)
    private String pathImage;

    @OneToOne(mappedBy="image", fetch = FetchType.LAZY)
    private Overcast overcast;

    @OneToOne(mappedBy="image", fetch = FetchType.LAZY)
    private WeatherCondition weatherCondition;

    @OneToOne(mappedBy="image", fetch = FetchType.LAZY)
    private FootWear footWear;

    @OneToOne(mappedBy="image", fetch = FetchType.LAZY)
    private Accessory accessory;

    @OneToOne(mappedBy="image", fetch = FetchType.LAZY)
    private UnderWear underWear;

    @OneToOne(mappedBy="image", fetch = FetchType.LAZY)
    private OuterWear outerWear;

    @OneToOne(mappedBy="image", fetch = FetchType.LAZY)
    private Cap cap;

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
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

    public FootWear getFootWear() {
        return footWear;
    }

    public void setFootWear(FootWear footWear) {
        this.footWear = footWear;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }

    public UnderWear getUnderWear() {
        return underWear;
    }

    public void setUnderWear(UnderWear underWear) {
        this.underWear = underWear;
    }

    public OuterWear getOuterWear() {
        return outerWear;
    }

    public void setOuterWear(OuterWear outerWear) {
        this.outerWear = outerWear;
    }

    public Cap getCap() {
        return cap;
    }

    public void setCap(Cap cap) {
        this.cap = cap;
    }
}
