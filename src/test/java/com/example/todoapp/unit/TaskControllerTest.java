package com.example.todoapp.unit;

import com.example.todoapp.controllers.TasksController;
import com.example.todoapp.models.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    Task t1 = new Task("test1", "description for test1");

    private static String stringAsJson(final Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    @Test
//    public void createTask() throws Exception {
//        this.mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content(stringAsJson(t1))
//        ).andDo(
//                print()
//        ).andExpect(
//                status().isCreated()
//        ).andExpect(
//                content().string(containsString("test1"))
//        );
//    }

//    @Test
//    public void createTask() throws Exception {
//    this.mockMvc.perform(get("/todos"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("/todos/add"));
//    }

    @Test
    public void viewAllTodo() throws Exception {
        this.mockMvc.perform(get("/todos").contentType(MediaType.APPLICATION_JSON).content(stringAsJson(t1))
        ).andDo(
                print()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void viewATodo() throws Exception {
        this.mockMvc.perform(get("/todos").contentType(MediaType.APPLICATION_JSON).content(stringAsJson(t1))
        ).andDo(
                print()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void createTodo() throws Exception {
        this.mockMvc.perform(get("/todos/add").contentType(MediaType.APPLICATION_JSON).content(stringAsJson(t1))
        ).andDo(
                print()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void viewByStatus() throws Exception {
        this.mockMvc.perform(get("/todos/status").contentType(MediaType.APPLICATION_JSON).content(stringAsJson(t1))
        ).andDo(
                print()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void viewTodo() throws Exception {
        ;
        this.mockMvc.perform(get("/todo/").contentType(MediaType.APPLICATION_JSON).content(stringAsJson(t1))
        ).andDo(
                print()
        ).andExpect(
                status().isOk()
        );
    }

}
