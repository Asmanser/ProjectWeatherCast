package by.andersen.training.repositories;

import by.andersen.training.models.OuterWear;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OuterWearRepository extends CrudRepository<OuterWear, Long> {

    @EntityGraph(attributePaths = {"image"})
    Iterable<OuterWear> findWithAllLazyAll();

    @EntityGraph(attributePaths = {"image"})
    Optional<OuterWear> findWithAllLazyById(Long id);

}
