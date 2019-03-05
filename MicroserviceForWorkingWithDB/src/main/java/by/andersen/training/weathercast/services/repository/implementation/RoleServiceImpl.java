package by.andersen.training.weathercast.services.repository.implementation;

import by.andersen.training.weathercast.models.Role;
import by.andersen.training.weathercast.repositories.RoleRepository;
import by.andersen.training.weathercast.services.repository.interfaces.RoleService;
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
        List<Role> roles = new ArrayList<>();
        for(Role role : roleRepository.findAll()) {
            roles.add(role);
        }
        return roles;
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
