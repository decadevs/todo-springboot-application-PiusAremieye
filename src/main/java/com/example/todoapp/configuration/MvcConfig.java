package com.example.todoapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/add").setViewName("add");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/todo").setViewName("todo");
        registry.addViewController("/todos").setViewName("todos");
        registry.addViewController("/login").setViewName("login");
    }
}
