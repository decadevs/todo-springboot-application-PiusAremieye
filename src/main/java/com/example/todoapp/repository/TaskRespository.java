package com.example.todoapp.repository;

import com.example.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRespository extends JpaRepository<Task, Integer>{
    List<Task> findByStatus(String status);
}
