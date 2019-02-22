package by.andersen.training.repositories;

import by.andersen.training.models.PersonalInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInformationRepository extends CrudRepository<PersonalInformation, Long> {
}
