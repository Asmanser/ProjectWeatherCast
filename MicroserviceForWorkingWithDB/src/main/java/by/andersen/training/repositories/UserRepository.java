package by.andersen.training.repositories;

import by.andersen.training.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @EntityGraph(attributePaths = {"personalInformation", "roles","personalInformation.city",
    "personalInformation.city.country"})
    Iterable<User> findAll();

    @EntityGraph(attributePaths = {"personalInformation", "roles","personalInformation.city",
            "personalInformation.city.country"})
    Optional<User> findLazyById(Long id);

}
