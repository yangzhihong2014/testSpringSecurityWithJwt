package com.example.testSpringSecurityWithJwt.service;

import com.example.testSpringSecurityWithJwt.model.User;

public interface AuthService {
    String login(String username, String password);
    String refresh(String oldToken);
}