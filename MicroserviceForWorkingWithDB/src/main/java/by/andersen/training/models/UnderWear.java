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
@Table(name = "underwear")
public class UnderWear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_underwear",length = 60, nullable = false)
    private String nameUnderWear;

    @OneToMany(mappedBy="underWear", fetch = FetchType.LAZY)
    private List<WeatherClothing> weatherClothings;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_image")
    private Image image;

    public UnderWear() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUnderWear() {
        return nameUnderWear;
    }

    public void setNameUnderWear(String nameUnderWear) {
        this.nameUnderWear = nameUnderWear;
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
