package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
}
