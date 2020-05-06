package com.example.usersandroles.config;

import com.example.usersandroles.enums.ApplicationRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.usersandroles.enums.ApplicationRoles.*;

@Configuration
@EnableWebSecurity
public class BasicAuthorizationConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/v1/students/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userDetailsOne = User.builder()
                .username("rohit")
                .password(passwordEncoder.encode("rohit"))
                .roles(STUDENT.name())
                .build();
        UserDetails userDetailsTwo = User.builder()
                .username("pragati")
                .password(passwordEncoder.encode("pragati"))
                .roles(ADMIN.name())
                .build();
        return new InMemoryUserDetailsManager(userDetailsOne, userDetailsTwo);
    }
}
