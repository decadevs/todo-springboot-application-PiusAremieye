package com.example.todoapp.controllers;

import com.example.todoapp.configuration.JwtProvider;
import com.example.todoapp.models.AuthResponse;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.User;
import com.example.todoapp.services.MyUserDetailsService;
import com.example.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TasksController{

    private TaskService taskService;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public TasksController(MyUserDetailsService myUserDetailsService, TaskService taskService, AuthenticationManager authenticationManager, JwtProvider jwtProvider){
      this.myUserDetailsService = myUserDetailsService;
      this.taskService = taskService;
      this.authenticationManager = authenticationManager;
      this.jwtProvider = jwtProvider;
    }

    @RequestMapping(path = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        }catch (BadCredentialsException ex){
            throw new Exception("not correct", ex);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUserName());
        final String jwt = jwtProvider.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @RequestMapping(path = "/todos/add", method = RequestMethod.GET)
    public String createTodo(Model model){
        model.addAttribute("newTodo", new Task());
        return "add";
    }

    @RequestMapping(path = "/todos", method = RequestMethod.POST)
    public String saveTodo(Task task){
        taskService.createAndEditTask(task);
        return "redirect:/todos";
    }

    @RequestMapping(path = "/todo/{id}", method = RequestMethod.GET)
    public String viewTodo(Model model, @PathVariable Integer id){
        model.addAttribute("todoById", taskService.viewTask(id));
        return "todo";
    }

    @RequestMapping(path = "/todos/status", method = RequestMethod.GET)
    public String viewTodoByStatus(Model model, @RequestParam(value = "search", required = false) String status){
        model.addAttribute("todos", taskService.viewByStatus(status));
        return "todos";
    }

    @RequestMapping(path = "/todos", method = RequestMethod.GET)
    public String viewAllTodo(Model model){
        model.addAttribute("todos", taskService.viewAllTask());
        return "todos";
    }

    @RequestMapping(path = "/todo/edit/{id}", method = RequestMethod.GET)
    public String editTodo(Model model, @PathVariable Integer id){
       model.addAttribute("editTodo", taskService.findTask(id));
       return "edit";
    }

    @RequestMapping(path = "/todo/delete/{id}", method = RequestMethod.GET)
    public String deleteTodo(@PathVariable Integer id){
        taskService.deleteTask(id);
        return "redirect:/todos";
    }

}