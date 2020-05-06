package com.example.userauthorities.jwt;

import lombok.Data;

@Data
public class UsernamePasswordAuthenticationRequest {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationRequest() {
    }

}
