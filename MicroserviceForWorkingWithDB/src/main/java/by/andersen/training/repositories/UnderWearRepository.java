package by.andersen.training.repositories;

import by.andersen.training.models.UnderWear;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnderWearRepository extends CrudRepository<UnderWear, Long> {

    @EntityGraph(attributePaths = {"image"})
    Iterable<UnderWear> findAll();

    @EntityGraph(attributePaths = {"image"})
    Optional<UnderWear> findLazyById(Long id);

}
