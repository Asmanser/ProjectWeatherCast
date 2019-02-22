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
@Table(name = "outerwear")
public class OuterWear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_outerwear", length = 60, nullable = false)
    private String nameOuterWear;

    @OneToMany(mappedBy="outerWear", fetch= FetchType.LAZY)
    private List<WeatherClothing> weatherClothings;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_image")
    private Image image;

    public OuterWear() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOuterWear() {
        return nameOuterWear;
    }

    public void setNameOuterWear(String nameOuterWear) {
        this.nameOuterWear = nameOuterWear;
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
