package by.andersen.training.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accessories")
public class Accessories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_accessorie", length = 60, nullable = false)
    private String nameAccessorie;

    public Accessories() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAccessorie() {
        return nameAccessorie;
    }

    public void setNameAccessorie(String nameAccessorie) {
        this.nameAccessorie = nameAccessorie;
    }
}
