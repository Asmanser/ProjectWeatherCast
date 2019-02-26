package by.andersen.training.repositories;

import by.andersen.training.models.Cap;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CapRepository extends CrudRepository<Cap, Long> {

    @EntityGraph(attributePaths = {"image"})
    Iterable<Cap> findAll();

    @EntityGraph(attributePaths = {"image"})
    Optional<Cap> findLazyById(Long id);

}
