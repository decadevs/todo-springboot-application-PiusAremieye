package com.example.todoapp.controllers;

import com.example.todoapp.models.Task;
import com.example.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TasksController{

    private TaskService taskService;

    @Autowired
    public TasksController(TaskService taskService){
      this.taskService = taskService;
    }

    @RequestMapping(path = "/")
    public String index(){
        return "index";
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