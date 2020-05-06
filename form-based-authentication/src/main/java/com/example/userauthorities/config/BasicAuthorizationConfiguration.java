package com.example.userauthorities.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.userauthorities.enums.ApplicationRoles.*;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicAuthorizationConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/v1/students/**").hasRole(STUDENT.name())
                /*.antMatchers(HttpMethod.DELETE, "/management/v1/students/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/management/v1/students/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/v1/students/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/management/v1/students/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())*/
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/courses");

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userDetailsOne = User.builder()
                .username("rohit")
                .password(passwordEncoder.encode("rohit"))
                //  .roles(STUDENT.name())
                .authorities(STUDENT.getSimpleGrantedAuthorities())
                .build();
        UserDetails userDetailsTwo = User.builder()
                .username("pragati")
                .password(passwordEncoder.encode("pragati"))
                //   .roles(ADMIN.name())
                .authorities(ADMIN.getSimpleGrantedAuthorities())
                .build();
        UserDetails userDetailsThree = User.builder()
                .username("neeraj")
                .password(passwordEncoder.encode("neeraj"))
                //   .roles(ADMIN_TRAINEE.name())
                .authorities(ADMIN_TRAINEE.getSimpleGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(userDetailsOne, userDetailsTwo, userDetailsThree);
    }
}
