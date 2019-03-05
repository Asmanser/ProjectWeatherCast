package by.andersen.training.weathercast.services.implementation;

import by.andersen.training.weathercast.models.Role;
import by.andersen.training.weathercast.models.User;
import by.andersen.training.weathercast.models.rabbitmq.UserRMQ;
import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
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

    public UserDetailsServiceImpl() {    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        sendMessageAndAcceptResponseRMQ.setRPC_Queue_Name("user");
        User login = new User();
        login.setLogin("s");
        sendMessageAndAcceptResponseRMQ.setMessage(gson.toJson(new UserRMQ("GETBYLOGIN",login)));
        Thread thread = new Thread(sendMessageAndAcceptResponseRMQ);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User user = gson.fromJson(sendMessageAndAcceptResponseRMQ.getAnswer(),User.class);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),grantedAuthorities);
    }
}
