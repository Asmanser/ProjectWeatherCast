package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.Overcast;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OvercastRepository extends CrudRepository<Overcast, Long> {

    @EntityGraph(attributePaths = {"image"})
    Iterable<Overcast> findAll();

    @EntityGraph(attributePaths = {"image"})
    Optional<Overcast> findLazyById(Long id);

}
