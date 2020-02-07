package com.example.todoapp.unit;

import com.example.todoapp.controllers.TasksController;
import com.example.todoapp.models.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TasksController tasksController;

    private static String stringAsJson(final Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    public void createTask() throws Exception {
        Task t1 = new Task("test1", "test description1", "test status1");Task t2 = new Task("test2", "test description2", "test status2");
        this.mockMvc.perform(post("/todo").contentType(MediaType.APPLICATION_JSON).content(stringAsJson(t1))
        ).andDo(
                print()
        ).andExpect(
                status().isCreated()
        ).andExpect(
                content().string(containsString("test1"))
        );
    }

    @Test
    public void viewTodo(){

    }

}
