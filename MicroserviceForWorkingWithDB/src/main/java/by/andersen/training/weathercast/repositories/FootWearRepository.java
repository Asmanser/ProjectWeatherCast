package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.FootWear;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FootWearRepository extends CrudRepository<FootWear, Long> {

    @EntityGraph(attributePaths = {"image"})
    Iterable<FootWear> findAll();

    @EntityGraph(attributePaths = {"image"})
    Optional<FootWear> findLazyById(Long id);

}
