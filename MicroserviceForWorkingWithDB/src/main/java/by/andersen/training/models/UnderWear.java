package by.andersen.training.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "underwear")
public class UnderWear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_underwear",length = 60, nullable = false)
    private String nameUnderWear;

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
}
