package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.PersonalInformation;

import java.util.List;

public interface PersonalInformationService extends CrudService<PersonalInformation, Long> {

    List<PersonalInformation> findWithAllLazyAll();

    PersonalInformation findWithAllLazyById(Long id);

}
