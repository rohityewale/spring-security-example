package com.example.userauthorities.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.userauthorities.enums.ApplicationRoles.*;

@Repository("fake")
public class FakeApplicationUserDaoImpl implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUserDetails> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUserDetails -> applicationUserDetails.getUsername().equals(username))
                .findFirst();
    }

    private List<ApplicationUserDetails> getApplicationUsers() {
        List<ApplicationUserDetails> applicationUserDetails = Lists.newArrayList(
                new ApplicationUserDetails(STUDENT.getSimpleGrantedAuthorities(),
                        "rohit",
                        passwordEncoder.encode("rohit"),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUserDetails(ADMIN.getSimpleGrantedAuthorities(),
                        "pragati",
                        passwordEncoder.encode("pragati"),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUserDetails(ADMIN_TRAINEE.getSimpleGrantedAuthorities(),
                        "neeraj",
                        passwordEncoder.encode("neeraj"),
                        true,
                        true,
                        true,
                        true)

                );

        return applicationUserDetails;

    }
}
