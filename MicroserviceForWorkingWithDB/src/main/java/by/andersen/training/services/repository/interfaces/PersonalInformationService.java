package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.PersonalInformation;

import java.util.List;

public interface PersonalInformationService extends CrudService<PersonalInformation, Long> {

    List<PersonalInformation> findWithAllLazyAll();

    PersonalInformation findWithAllLazyById(Long id);

}
