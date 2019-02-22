package by.andersen.training.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "overcast")
public class Overcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_overcast", length = 50, nullable = false)
    private String nameOvercast;

    public Overcast(String nameOvercast) {
        this.nameOvercast = nameOvercast;
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
}
