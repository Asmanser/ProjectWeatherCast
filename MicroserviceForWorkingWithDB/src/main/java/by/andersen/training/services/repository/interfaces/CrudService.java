package by.andersen.training.services.repository.interfaces;

import java.util.List;

public interface CrudService<T,K> {

    void save(T t);

    void saveAll(List<T> tList);

    T findById(K id);

    boolean existsById(K id);

    List<T> findAll();

    long count();

    void deleteById(K id);

    void delete(T t);

    void deleteAll();

}
