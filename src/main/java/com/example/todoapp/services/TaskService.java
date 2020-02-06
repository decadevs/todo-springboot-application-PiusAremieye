package com.example.todoapp.services;

import com.example.todoapp.models.Task;
import com.example.todoapp.repository.TaskRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
