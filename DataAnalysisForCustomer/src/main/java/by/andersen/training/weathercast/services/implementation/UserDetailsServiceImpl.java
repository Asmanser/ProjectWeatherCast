package by.andersen.training.weathercast.services.implementation;

import by.andersen.training.weathercast.models.Role;
import by.andersen.training.weathercast.models.User;
import by.andersen.training.weathercast.models.rabbitmq.UserRMQ;
import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
import by.andersen.training.weathercast.services.interfaces.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ;

    @Autowired
    private Gson gson;

    @Autowired
    private UserService userService;

    public UserDetailsServiceImpl() { }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.getByLogin(s);

        if(user == null) {
            throw new UsernameNotFoundException("Login " + s + "doesn't found!");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
        return springUser;
    }
}
