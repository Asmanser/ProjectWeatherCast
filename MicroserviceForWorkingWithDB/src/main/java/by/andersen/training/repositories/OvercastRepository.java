package by.andersen.training.repositories;

import by.andersen.training.models.Overcast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OvercastRepository extends CrudRepository<Overcast, Long> {
}
