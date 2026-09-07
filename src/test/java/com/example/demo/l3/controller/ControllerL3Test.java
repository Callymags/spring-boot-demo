package com.example.demo.l3.controller;

import com.example.demo.l3.model.CategoryL3;
import com.example.demo.l3.service.CategoryServiceL3;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// STEP 1: Use @WebMvcTest to test CategoryControllerL3
// STEP 2: Autowire MockMvc for HTTP request testing
// STEP 3: Mock CategoryServiceL3 using @MockitoBean
// STEP 4: Test getCategory endpoint with a valid id
// STEP 5: Test getCategory endpoint with an invalid id

@WebMvcTest(CategoryControllerL3.class)
public class ControllerL3Test {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    CategoryServiceL3 categoryServiceL3;

    @Test
    void getCategory_returnsCategory_whenValidIdProvided() throws Exception {

        CategoryL3 category =
                new CategoryL3(1L, "Travel", "Travel Desc");

        when(categoryServiceL3.getCategoryById(1L))
                .thenReturn(Optional.of(category));

        mockMvc.perform(get("/api/public/l3/getCategory/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.categoryName").value("Travel"))
                .andExpect(jsonPath("$.categoryDesc").value("Travel Desc"));
    }

    @Test
    void getCategory_returnsEmpty_whenInvalidIdProvided() throws Exception {

        when(categoryServiceL3.getCategoryById(99L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/public/l3/getCategory/{id}", 99L))
                .andExpect(status().isOk())
                .andExpect(content().string("null"));
    }

    @Test
    void addCategory_returnsConfirmation_whenValidCategoryProvided() throws Exception {

        CategoryL3 category =
                new CategoryL3(4L, "Music", "Music Desc");

        when(categoryServiceL3.addCategory(any(CategoryL3.class)))
                .thenReturn("Category added: " + category);

        mockMvc.perform(post("/api/public/l3/addCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Category added:")));
    }


}
