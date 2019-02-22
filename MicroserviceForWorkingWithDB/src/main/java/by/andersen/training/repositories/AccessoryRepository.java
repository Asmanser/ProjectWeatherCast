package by.andersen.training.repositories;

import by.andersen.training.models.Accessory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends CrudRepository<Accessory, Long> {
}
