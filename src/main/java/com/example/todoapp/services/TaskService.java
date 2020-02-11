package com.example.todoapp.services;

import com.example.todoapp.models.Task;
import com.example.todoapp.repository.TaskRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private TaskRespository taskRespository;

    @Autowired
    public TaskService(TaskRespository taskRespository){
        this.taskRespository = taskRespository;
    }

    public Task createAndEditTask(Task task){
        Task todo = taskRespository.save(task);
        try{
            todo.setTitle(task.getTitle());
            todo.setDescription(task.getDescription());

            Date time = new Date();
            long newTime = time.getTime();
            Timestamp completedTime = new Timestamp(newTime);
            String status = task.getStatus();
            if (status.equalsIgnoreCase("completed")){
                todo.setStatus(status);
                todo.setCompletedAt(completedTime);
            }
            else{
                todo.setStatus(status);
                todo.setCompletedAt(null);
            }
            taskRespository.save(todo);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return todo;
    }

    public Task viewTask(Integer id){
        Task t = null;
        try{
            t = findTask(id);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return t;
    }

    public List<Task> viewAllTask(){
        List<Task> t = null;
        try{
            t = taskRespository.findAll();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return t;
    }

    public Task findTask(Integer id){
        Task todo = taskRespository.findById(id).orElse(null);
        if (todo != null){
            return taskRespository.save(todo);
        }
        return null;
    }

    public String deleteTask(Integer id){
        if (taskRespository.existsById(id)){
            taskRespository.deleteById(id);
            return "Todo deleted successfully";
        }
        return null;
    }

    public List<Task> viewByStatus(String status){
        return taskRespository.findByStatus(status);
    }
}