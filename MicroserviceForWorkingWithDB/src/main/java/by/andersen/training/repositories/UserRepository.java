package by.andersen.training.repositories;

import by.andersen.training.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @EntityGraph(attributePaths = {"personalInformation", "roles"})
    Iterable<User> findWithAllLazyAll();

    @EntityGraph(attributePaths = {"personalInformation", "roles"})
    Optional<User> findWithAllLazyById(Long id);

}
