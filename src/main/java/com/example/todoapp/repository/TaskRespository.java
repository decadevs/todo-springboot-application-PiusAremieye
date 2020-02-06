package com.example.todoapp.repository;

import com.example.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRespository extends JpaRepository<Task, Integer>{

}
