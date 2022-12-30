package com.example.demo.main.auth;

import jakarta.transaction.Transactional;


@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public boolean isAuthenticated(User user) {
        System.out.println("is authenticated new user: "+user.getLogin());
        return userRepository.existsById(user.getLogin());
    }

    @Override
    public Status authorised(User user) {
        if(!isAuthenticated(user)){
            return Status.THIS_LOGIN_IS_NOT_EXIST;
        }
        if(!userRepository.exists(user)){
            return Status.WRONG_PASSWORD;
        }
        return Status.SUCCESS;
    }

    @Override
    public Status register(User user) {
        System.out.println("add new user: "+user.getLogin());
        if(!isAuthenticated(user)) {
            userRepository.save(user);
            return Status.SUCCESS;
        }
        return Status.THIS_LOGIN_ALREADY_EXIST;
    }

}
