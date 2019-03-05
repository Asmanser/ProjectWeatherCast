package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.Accessory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessoryRepository extends CrudRepository<Accessory, Long> {

    @EntityGraph(attributePaths = {"image"})
    Iterable<Accessory> findAll();

    @EntityGraph(attributePaths = {"image"})
    Optional<Accessory> findLazyById(Long id);
}
