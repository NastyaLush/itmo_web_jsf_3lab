package com.example.demo.main.auth;

public interface UserService {

    public boolean isAuthenticated(User user);
    public Status authorised(User user);
    public Status register(User user);
}
