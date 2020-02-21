package com.example.todoapp.controllers;

import com.example.todoapp.models.User;
import com.example.todoapp.services.MyUserDetailsService;
import com.example.todoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String createUser(Model model){
        model.addAttribute("newUser", new User());
        return "signup";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String signup(Model model, User user){
        model.addAttribute("user", userService.create(user));
        return "redirect:/todos";
    }

}
