package by.andersen.training.weathercast.services.interfaces;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
