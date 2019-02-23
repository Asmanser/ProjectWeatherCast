package by.andersen.training.repositories;

import by.andersen.training.models.PersonalInformation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalInformationRepository extends CrudRepository<PersonalInformation, Long> {

    @EntityGraph(attributePaths = {"city"})
    Iterable<PersonalInformation> findWithAllLazyAll();

    @EntityGraph(attributePaths = {"city"})
    Optional<PersonalInformation> findWithAllLazyById(Long id);

}
