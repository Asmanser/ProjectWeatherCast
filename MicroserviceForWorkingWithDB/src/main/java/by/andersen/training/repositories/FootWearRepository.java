package by.andersen.training.repositories;

import by.andersen.training.models.FootWear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootWearRepository extends CrudRepository<FootWear, Long> {
}
