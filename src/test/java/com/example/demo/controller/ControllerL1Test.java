package com.example.demo.controller;

import com.example.demo.model.CategoryL1;
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

@WebMvcTest
public class ControllerL1Test {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryControllerL1 controller;

    private List<CategoryL1> testCategories;


//    @Test
//    void getCategories_returnsEmptyList_whenNoCatAdded() throws Exception{
//        mockMvc.perform(get("/api/public/l1/getCategories"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[]"));
//    }

    @BeforeEach
    void setup() {
        testCategories = new ArrayList<>();

        testCategories.add(new CategoryL1(1L, "Sport", "Test"));
        testCategories.add(new CategoryL1(2L, "Travel", "Test"));

        ReflectionTestUtils.setField(controller, "categories", testCategories);
    }

    @Test
    void getCategories_returnsList_whenCategoriesExist() throws Exception {
        mockMvc.perform(get("/api/public/l1/getCategories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].categoryName").value("Sport"))
                .andExpect(jsonPath("$[1].categoryName").value("Travel"));
    }
}
