package com.example.todoapp.unit;

import com.example.todoapp.controllers.TasksController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
public class TaskTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TasksController tasksController;

    @Test
    public void testTaskController(){
        assertThat(tasksController).isNotNull();
    }

}
