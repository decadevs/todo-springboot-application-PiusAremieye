package com.example.todoapp.services;

import com.example.todoapp.models.MyUserDetails;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.UserDetailsRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserDetailsRespository userDetailsRespository;

    @Autowired
    public MyUserDetailsService(UserDetailsRespository userDetailsRespository){
        this.userDetailsRespository = userDetailsRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userDetailsRespository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return user.map(MyUserDetails::new).get();
    }

//    public User create(User user) throws Exception{
//        if (!myUserDetailsRespository.existByUsername(user.getUserName())){
//            System.out.println("hi");
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            return myUserDetailsRespository.save(user);
//        }
//        throw new Exception("Username " + user.getUserName() + " already exists");
//    }
//

}
