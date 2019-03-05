package by.andersen.training.weathercast.models;

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
@Table(name = "footwear")
public class FootWear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_footwear",length = 60,nullable = false)
    private String nameFootWear;

    @OneToMany(mappedBy="footWear", fetch= FetchType.LAZY)
    private List<WeatherClothing> weatherClothingList;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_image")
    private Image image;

    public FootWear() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameFootWear() {
        return nameFootWear;
    }

    public void setNameFootWear(String nameFootWear) {
        this.nameFootWear = nameFootWear;
    }

    public List<WeatherClothing> getWeatherClothingList() {
        return weatherClothingList;
    }

    public void setWeatherClothingList(List<WeatherClothing> weatherClothingList) {
        this.weatherClothingList = weatherClothingList;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
