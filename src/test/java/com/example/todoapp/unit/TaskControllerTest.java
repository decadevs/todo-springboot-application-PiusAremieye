//package com.example.todoapp.unit;
//
//import com.example.todoapp.controllers.TasksController;
//import com.example.todoapp.repository.TaskRespository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
//public class TaskControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private TasksController tasksController;
//
//    @Test
//    public void createTodo() throws Exception {
//        this.mockMvc.perform(get("/todos/add")
//        ).andDo(
//                print()
//        ).andExpect(
//                status().isOk()
//        ).andExpect(
//                model().attributeExists("newTodo")
//        );
//    }
//
//    @Test
//    public void viewAllTodo() throws Exception {
//        this.mockMvc.perform(get("/todos")
//        ).andDo(
//                print()
//        ).andExpect(
//                status().isOk()
//        ).andExpect(
//                model().attributeExists("todos")
//        );
//    }
//
//    @Test
//    public void viewByStatus() throws Exception {
//        this.mockMvc.perform(get("/todos/status")
//        ).andDo(
//                print()
//        ).andExpect(
//                status().isOk()
//        ).andExpect(
//                model().attributeExists("todos")
//        );
//    }
//
//    @Test
//    public void deleteTodo() throws Exception {
//        this.mockMvc.perform(get("/todo/delete/1")
//        ).andDo(
//                print()
//        ).andExpect(
//                status().is3xxRedirection()
//        ).andExpect(
//                redirectedUrl("/todos")
//        );
//    }
//
//    @Test
//    public void editTodo() throws Exception {
//        this.mockMvc.perform(get("/todo/edit/4")
//        ).andDo(
//                print()
//        ).andExpect(
//                status().isOk()
//        ).andExpect(
//                model().attributeExists("editTodo")
//        );
//    }
//
//    @Test
//    public void home() throws Exception {
//        this.mockMvc.perform(get("/")
//        ).andDo(
//                print()
//        ).andExpect(
//                status().isOk()
//        );
//    }
//
//}
