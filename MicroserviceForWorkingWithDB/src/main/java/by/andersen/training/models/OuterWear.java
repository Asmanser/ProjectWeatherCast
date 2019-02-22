package by.andersen.training.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "outerwear")
public class OuterWear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_outerwear", length = 60, nullable = false)
    private String nameOuterWear;

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
}
