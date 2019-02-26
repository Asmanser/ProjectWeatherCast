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
@Table(name = "accessories")
public class Accessory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_accessory", length = 60, nullable = false)
    private String nameAccessory;

    @OneToMany(mappedBy="accessory", fetch = FetchType.LAZY)
    private transient List<WeatherClothing> weatherClothings;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_image")
    private Image image;

    public Accessory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAccessory() {
        return nameAccessory;
    }

    public void setNameAccessory(String nameAccessory) {
        this.nameAccessory = nameAccessory;
    }

    public List<WeatherClothing> getWeatherClothings() {
        return weatherClothings;
    }

    public void setWeatherClothings(List<WeatherClothing> weatherClothings) {
        this.weatherClothings = weatherClothings;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
