package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.PersonalInformation;
import by.andersen.training.repositories.PersonalInformationRepository;
import by.andersen.training.services.repository.interfaces.PersonalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalInformationServiceImpl implements PersonalInformationService {

    @Autowired
    private PersonalInformationRepository personalInformationRepository;

    @Override
    public void save(PersonalInformation personalInformation) {
        personalInformationRepository.save(personalInformation);
    }

    @Override
    public void saveAll(List<PersonalInformation> personalInformations) {
        personalInformationRepository.saveAll(personalInformations);
    }

    @Override
    public PersonalInformation findById(Long id) {
        return personalInformationRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return personalInformationRepository.existsById(id);
    }

    @Override
    public List<PersonalInformation> findAll() {
        List<PersonalInformation> footWears = new ArrayList<>();
        for(PersonalInformation footWear : personalInformationRepository.findAll()) {
            footWears.add(footWear);
        }
        return footWears;
    }

    @Override
    public long count() {
        return personalInformationRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        personalInformationRepository.deleteById(id);
    }

    @Override
    public void delete(PersonalInformation personalInformation) {
        personalInformationRepository.delete(personalInformation);
    }

    @Override
    public void deleteAll() {
        personalInformationRepository.deleteAll();
    }


    @Override
    public Iterable<PersonalInformation> findWithAllLazyAll() {
        List<PersonalInformation> footWears = new ArrayList<>();
        for(PersonalInformation footWear : personalInformationRepository.findWithAllLazyAll()) {
            footWears.add(footWear);
        }
        return footWears;
    }

    @Override
    public PersonalInformation findWithAllLazyById(Long id) {
        return personalInformationRepository.findWithAllLazyById(id).get();
    }
}
