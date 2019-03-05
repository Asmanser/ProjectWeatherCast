package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image,Long> {
}
