package com.example.todoapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<String> Home(){
        List<String> li = new ArrayList<>();
        li.add("Home controller");
        li.add("Ai powered");
        return li;
    }

}
