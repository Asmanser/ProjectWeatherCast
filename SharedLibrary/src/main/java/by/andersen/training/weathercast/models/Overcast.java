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
@Table(name = "overcast")
public class Overcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_overcast", length = 50, nullable = false)
    private String nameOvercast;

    @OneToMany(mappedBy="overcast", fetch = FetchType.LAZY)
    private List<WeatherInformation> weatherInformations;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_image")
    private Image image;

    public Overcast() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOvercast() {
        return nameOvercast;
    }

    public void setNameOvercast(String nameOvercast) {
        this.nameOvercast = nameOvercast;
    }

    public List<WeatherInformation> getWeatherInformations() {
        return weatherInformations;
    }

    public void setWeatherInformations(List<WeatherInformation> weatherInformations) {
        this.weatherInformations = weatherInformations;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
