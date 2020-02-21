package com.example.todoapp.repository;

import com.example.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRespository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);
//    boolean existByUsername(String username);
}
