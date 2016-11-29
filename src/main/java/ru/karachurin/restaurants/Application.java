package ru.karachurin.restaurants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.karachurin.restaurants.repository.UserRepository;

/**
 * Created by Денис on 02.11.2016.
 */
@SpringBootApplication
@PropertySource(value="classpath:application")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

        @Autowired
        UserRepository userRepository;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService());
        }

        @Bean
        UserDetailsService userDetailsService() {
            return new UserDetailsService() {

                @Override
                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                    ru.karachurin.restaurants.model.User account = userRepository.findByName(username);
                    if(account != null) {
                        return new User(account.getName(), account.getPassword(), true, true, true, true,
                                username.equals("Admin") ? AuthorityUtils.createAuthorityList("USER", "ADMIN") :AuthorityUtils.createAuthorityList("USER"));
                    } else {
                        throw new UsernameNotFoundException("could not find the user '"
                                + username + "'");
                    }
                }
            };
        }

        @EnableWebSecurity
        @Configuration
        class WebSecurityConfig extends WebSecurityConfigurerAdapter {

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                        .antMatchers(HttpMethod.POST,"/rest/v1/profile/registration").permitAll()
                        .antMatchers("***/admin/***").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/rest/v1/restaurants/***").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/rest/v1/restaurants/***").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/rest/v1/restaurants/***").hasRole("ADMIN")
                        .anyRequest().fullyAuthenticated()
                        .and().httpBasic()
                        .and().csrf().disable();
            }
        }
    }
}
