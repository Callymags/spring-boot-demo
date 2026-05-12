package com.example.demo.controller;

import com.example.demo.model.CategoryL1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// STEP 1: Autowire fields and add correct class annotation
// STEP 2: setUp method to populate list
// STEP 3: Create a test to cover getCategories endpoint
// STEP 4: Create tests to cover getCategory endpoint - valid and invalid id
// STEP 5: Create a test to cover addCategory endpoint
// STEP 6: Create a test to cover updateCategory endpoint

@WebMvcTest
public class ControllerL1Test {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryControllerL1 categoryControllerL1;

    private List<CategoryL1> testCategories;

    @Autowired
    private ObjectMapper objectMapper;

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
    void getCategory_returnsCat_whenIdPresent() throws Exception {
        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").value("Travel"));
    }

    @Test
    void getCategory_returnsEmptyString_whenNoIdPresent() throws Exception {
        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 99L))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void addCategory_returnsString_whenValidCatPresented () throws Exception {
        CategoryL1 category = new CategoryL1(3L, "Food", "Test");

        mockMvc.perform(post("/api/public/l1/addCategory").contentType("application/json").content(toJson(category)))
                .andExpect(status().isOk()).andExpect(content().string(containsString("Category added")));

        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 3L))
                .andExpect(status().isOk()).andExpect(jsonPath("$.categoryName").value("Food"));

    }


    @Test
    void updateCategory_returnsStringAndUpdatesCategory_whenIdPresent() throws Exception {
        CategoryL1 categoryUpdates = new CategoryL1(1L, "Updated Travel", "Updated Test");

        mockMvc.perform(put("/api/public/l1/updateCategory/{id}", 1L)
                        .contentType("application/json")
                        .content(toJson(categoryUpdates)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Updated Category")));

        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").value("Updated Travel"))
                .andExpect(jsonPath("$.categoryDesc").value("Updated Test"));
    }

    @Test
    void updateCategory_returnsEmptyString_whenIdNotPresent() throws Exception {
        CategoryL1 categoryUpdates = new CategoryL1(99L, "Updated Name", "Updated Desc");

        mockMvc.perform(put("/api/public/l1/updateCategory/{id}", 99L)
                        .contentType("application/json")
                        .content(toJson(categoryUpdates)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    private String toJson(CategoryL1 category) throws Exception {
        return objectMapper.writeValueAsString(category);
    }
}
