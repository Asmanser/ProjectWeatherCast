package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
