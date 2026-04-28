package com.example.demo.controller;

import com.example.demo.model.CategoryL1;
import jdk.jfr.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// STEP 1: Autowire fields and add correct class annotation
// STEP 2: setUp method to populate list
// STEP 3: Create a test to cover getCategories endpoint
@WebMvcTest
public class ControllerL1Test {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryControllerL1 categoryControllerL1;

    private List<CategoryL1> testCategories;

    @BeforeEach
    void setUp(){
        testCategories = new ArrayList<>();

        testCategories.add(new CategoryL1(1L, "Travel", "Test"));
        testCategories.add(new CategoryL1(2L, "Sport", "Test"));
        ReflectionTestUtils.setField(categoryControllerL1, "categories", testCategories);
    }

    @Test
    void getCategories_returnsList_whenCatPresent() throws Exception{
        mockMvc.perform(get("/api/public/l1/getCategories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].categoryName").value("Travel"))
                .andExpect(jsonPath("$[1].categoryName").value("Sport"));
    }

    @Test
    void getCategory_returnsCategory_whenIdExists() throws Exception {
        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1))
                .andExpect(jsonPath("$.categoryName").value("Travel"));
    }

    @Test
    void getCategory_returnsEmptyBody_whenIdDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 99L))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
