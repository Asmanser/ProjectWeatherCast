package by.andersen.training.repositories;

import by.andersen.training.models.FootWear;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FootWearRepository extends CrudRepository<FootWear, Long> {

    @EntityGraph(attributePaths = {"image"})
    Iterable<FootWear> findWithAllLazyAll();

    @EntityGraph(attributePaths = {"image"})
    Optional<FootWear> findWithAllLazyById(Long id);

}
