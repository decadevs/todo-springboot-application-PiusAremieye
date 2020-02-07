package com.example.todoapp.services;

import com.example.todoapp.models.Task;
import com.example.todoapp.repository.TaskRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRespository taskRespository;

    public Task createTask(Task task){
        Task t = null;
        try{
            t = taskRespository.save(task);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return t;
    }

    public Task viewTask(Integer id){
        Task t = null;
        try{
            t = taskRespository.findById(id).orElse(null);
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

    public Task updateTask(Task taskToUpdate, Integer id){
        Task todo = taskRespository.findById(id).orElse(null);
        if (todo != null){
            todo.setTitle(taskToUpdate.getTitle());
            todo.setDescription(taskToUpdate.getDescription());
            Timestamp time = todo.getUpdateAt();
            String status = taskToUpdate.getStatus();
            if (status.equalsIgnoreCase("completed")){
                todo.setStatus("completed");
                todo.setCompletedAt(time);
            }
            else{
                todo.setStatus(status);
                todo.setCompletedAt(null);
            }
            return taskRespository.save(todo);
        }
        return null;
    }

    public Integer deleteTask(Integer id){
        if (taskRespository.existsById(id)){
            taskRespository.deleteById(id);
            return id;
        }
        return null;
    }

    public List<Task> ViewByStatus(String status){
        return taskRespository.findByStatus(status);
    }
}
