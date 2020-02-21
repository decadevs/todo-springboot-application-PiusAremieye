package com.example.todoapp.services;

import com.example.todoapp.models.User;
import com.example.todoapp.repository.UserDetailsRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDetailsRespository userDetailsRespository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDetailsRespository userDetailsRespository, PasswordEncoder passwordEncoder){
        this.userDetailsRespository = userDetailsRespository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDetailsRespository.save(user);
    }
}
