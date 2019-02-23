package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.Image;
import by.andersen.training.repositories.ImageRepository;
import by.andersen.training.services.repository.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void saveAll(List<Image> images) {
        imageRepository.saveAll(images);
    }

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return imageRepository.existsById(id);
    }

    @Override
    public List<Image> findAll() {
        List<Image> footWears = new ArrayList<>();
        for(Image footWear : imageRepository.findAll()) {
            footWears.add(footWear);
        }
        return footWears;
    }

    @Override
    public long count() {
        return imageRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public void delete(Image image) {
        imageRepository.delete(image);
    }

    @Override
    public void deleteAll() {
        imageRepository.deleteAll();
    }
}
