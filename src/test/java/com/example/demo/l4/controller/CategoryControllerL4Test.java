package com.example.demo.l4.controller;

import com.example.demo.l4.model.CategoryL4;
import com.example.demo.l4.service.CategoryServiceL4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryControllerL4.class)
public class CategoryControllerL4Test {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryServiceL4 categoryServiceL4;

    @Test
    void getCategory_returnsCategory_whenIdExists() throws Exception {

        CategoryL4 category =
                new CategoryL4(1L, "Travel", "Travel Desc");

        when(categoryServiceL4.getCategoryById(1L))
                .thenReturn(Optional.of(category));

        mockMvc.perform(get("/api/public/l4/getCategory/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.categoryName").value("Travel"))
                .andExpect(jsonPath("$.categoryDesc").value("Travel Desc"));
    }

    @Test
    void getCategory_returnsEmpty_whenIdDoesNotExist() throws Exception {

        when(categoryServiceL4.getCategoryById(99L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/public/l4/getCategory/{id}", 99L))
                .andExpect(status().isOk())
                .andExpect(content().string("null"));
    }
}