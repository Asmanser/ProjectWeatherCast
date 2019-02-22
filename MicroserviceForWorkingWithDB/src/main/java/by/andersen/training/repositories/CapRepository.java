package by.andersen.training.repositories;

import by.andersen.training.models.Cap;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapRepository extends CrudRepository<Cap, Long> {
}
