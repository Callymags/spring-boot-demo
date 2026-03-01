package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryControllerL1.class)
public class ControllerL1Test {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCategories_returnsEmptyArray_whenNoCategoriesAdded() throws Exception {
        mockMvc.perform(get("/api/public/l1/getCategories"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
