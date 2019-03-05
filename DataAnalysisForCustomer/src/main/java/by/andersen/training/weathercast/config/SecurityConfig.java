package by.andersen.training.weathercast.config;

import by.andersen.training.weathercast.services.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected void registerGlobalAuthentication(AuthenticationManagerBuilder registry) throws Exception {
        registry
                .userDetailsService(userDetailsService())
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/resources/**").permitAll()
                /*.antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/receiver/**").hasRole("MANAGER")*/
                .antMatchers("/**").hasAnyRole("ADMIN", "USER", "MANAGER")
                .and().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successForwardUrl("/")
                .passwordParameter("password")
                .usernameParameter("username")
                .permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").permitAll();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}
