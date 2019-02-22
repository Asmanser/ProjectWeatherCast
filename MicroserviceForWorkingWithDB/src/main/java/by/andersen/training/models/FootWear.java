package by.andersen.training.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "footwear")
public class FootWear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_footwear",length = 60,nullable = false)
    private String nameFootWear;

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
}
