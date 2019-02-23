package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.Role;
import by.andersen.training.repositories.RoleRepository;
import by.andersen.training.services.repository.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void saveAll(List<Role> roles) {
        roleRepository.saveAll(roles);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public List<Role> findAll() {
        List<Role> footWears = new ArrayList<>();
        for(Role footWear : roleRepository.findAll()) {
            footWears.add(footWear);
        }
        return footWears;
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

}
