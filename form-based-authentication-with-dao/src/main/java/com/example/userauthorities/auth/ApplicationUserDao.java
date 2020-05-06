package com.example.userauthorities.auth;


import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUserDetails> selectApplicationUserByUsername(String username);
}
