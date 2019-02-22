package by.andersen.training.repositories;

import by.andersen.training.models.UnderWear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnderWearRepository extends CrudRepository<UnderWear, Long> {

}
