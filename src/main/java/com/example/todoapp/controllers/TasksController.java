package com.example.todoapp.controllers;

import com.example.todoapp.models.Task;
import com.example.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Value("${server.por: 40}")
    private String port;
    @Value("${server.servlet.context-path}")
    private String path;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String HealthCheck(){
        return "Our app is running on port - " + port +
                " \nOur app path is - " + path;
    }

    @PostMapping
    public MyResponse<Task> createTask(@RequestBody Task task, HttpServletResponse response){
        Task t = taskService.createTask(task);
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "Todo created successfully";
        if (t == null){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "Really bad request, please leave!!!";
        }
        response.setStatus(statusCode.value());
        return new MyResponse<>(statusCode, message, t);
    }

    @GetMapping
    @RequestMapping("{id}")
    public MyResponse<Task> getOneTask(@PathVariable Integer id){
        Task t = taskService.getTask(id);
        if (t == null){
            return new MyResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "No task with this id: " + id, null);
        }
        return new MyResponse<>(HttpStatus.OK, "Retrieved task successfully", t);
    }

    @GetMapping
    @RequestMapping("/All")
    public MyResponse<List<Task>> getAllTask(){
        List<Task> t = taskService.getAllTasks();
        if (t.isEmpty()){
            return new MyResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "No task(s) available", null);
        }
        return new MyResponse<>(HttpStatus.OK, "Retrieved task(s) successfully", t);
    }

    @GetMapping
    @RequestMapping("/Status/{status}")
    public MyResponse<List<Task>> findByStatus(@PathVariable String status){
        List<Task> t = taskService.findStatus(status);
        if (t.isEmpty()){
            return new MyResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "No task(s) available", null);
        }
        return new MyResponse<>(HttpStatus.OK, "Retrieved task(s) successfully", t);
    }
}
