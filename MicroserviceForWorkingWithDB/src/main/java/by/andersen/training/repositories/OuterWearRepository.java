package by.andersen.training.repositories;

import by.andersen.training.models.OuterWear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuterWearRepository extends CrudRepository<OuterWear, Long> {
}
