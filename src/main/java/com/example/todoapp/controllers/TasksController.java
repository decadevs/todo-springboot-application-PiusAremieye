package com.example.todoapp.controllers;

import com.example.todoapp.models.Task;
import com.example.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TasksController{

    @Autowired
    private TaskService taskService;

    @PostMapping
    public MyResponse<Task> creatTask(@RequestBody Task task, HttpServletResponse response){
        Task todo = taskService.createTask(task);
        HttpStatus status = HttpStatus.CREATED;
        String message = "Todo created successfully";
        if (todo == null){
            status = HttpStatus.BAD_REQUEST;
            message = "Todo cannot be created";
        }
        response.setStatus(status.value());
        return new MyResponse<>(status, message, todo);
    }

    @GetMapping
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public MyResponse<Task> viewOneTask(@PathVariable Integer id, HttpServletResponse response){
        Task todo = taskService.viewTask(id);
        HttpStatus status = HttpStatus.CREATED;
        String message = "Todo retrieved successfully";
        if (todo == null){
            status = HttpStatus.BAD_REQUEST;
            message = "Todo does not exist";
        }
        response.setStatus(status.value());
        return new MyResponse<>(status, message, todo);
    }


    @GetMapping
    @RequestMapping(value = "/all_todo", method = RequestMethod.GET)
    public MyResponse<List<Task>> viewAllTask(HttpServletResponse response){
        List<Task> todos = taskService.viewAllTask();
        HttpStatus status = HttpStatus.CREATED;
        String message = "All Todo's retrieved successfully";
        if (todos.isEmpty()){
            status = HttpStatus.BAD_REQUEST;
            message = "Todo's does not exist";
        }
        response.setStatus(status.value());
        return new MyResponse<>(status, message, todos);
    }

    @GetMapping
    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public MyResponse<List<Task>> viewStatus(@PathVariable String status, HttpServletResponse response){
        List<Task> todos = taskService.ViewByStatus(status);
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "All " + status + " Todo's retrieved successfully";
        if (todos.isEmpty()){
            statusCode = HttpStatus.BAD_REQUEST;
            message = status + " Todo's does not exist";
        }
        response.setStatus(statusCode.value());
        return new MyResponse<>(statusCode, message, todos);
    }

    @PatchMapping
    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public MyResponse<Task> updateTask(@RequestBody Task taskToUpdate, @PathVariable Integer id,  HttpServletResponse response){
        Task todo = taskService.updateTask(taskToUpdate, id);
        HttpStatus status = HttpStatus.CREATED;
        String message = "Todo updated successfully";
        if (todo == null){
            status = HttpStatus.BAD_REQUEST;
            message = "Todo does not exist";
        }
        response.setStatus(status.value());
        return new MyResponse<>(status, message, todo);
    }

    @DeleteMapping
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public MyResponse<Integer> deleteTask(@PathVariable Integer id, HttpServletResponse response){
        Integer todo = taskService.deleteTask(id);
        HttpStatus status = HttpStatus.CREATED;
        String message = "Todo deleted successfully";
        if (todo == null){
            status = HttpStatus.BAD_REQUEST;
            message = "Todo does not exist or was deleted";
        }
        response.setStatus(status.value());
        return new MyResponse<>(status, message, todo);
    }

}
