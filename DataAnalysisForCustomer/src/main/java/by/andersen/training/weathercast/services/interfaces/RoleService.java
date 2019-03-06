package by.andersen.training.weathercast.services.interfaces;

import by.andersen.training.weathercast.models.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRole() throws InterruptedException;

    public Role getById(long id) throws InterruptedException;

}
