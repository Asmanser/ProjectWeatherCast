package by.andersen.training.repositories;

import by.andersen.training.models.Overcast;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OvercastRepository extends CrudRepository<Overcast, Long> {

    @EntityGraph(attributePaths = {"image"})
    Iterable<Overcast> findWithAllLazyAll();

    @EntityGraph(attributePaths = {"image"})
    Optional<Overcast> findWithAllLazyById(Long id);

}
