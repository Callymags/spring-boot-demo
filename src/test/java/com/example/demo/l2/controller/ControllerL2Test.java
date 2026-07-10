package com.example.demo.l2.controller;

import com.example.demo.l2.model.CategoryL2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryControllerL2.class)
public class ControllerL2Test {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CategoryControllerL2 categoryControllerL2;

    private List<CategoryL2> testCategories;

    @BeforeEach
    void setUp() {
        testCategories = new ArrayList<>();

        testCategories.add(new CategoryL2(1L, "Travel", "Test"));
        testCategories.add(new CategoryL2(2L, "Sport", "Test"));

        ReflectionTestUtils.setField(categoryControllerL2, "categories", testCategories);
    }

    @Test
    void searchCategory_returnsList_whenValidNameProvided() throws Exception {
        mockMvc.perform(get("/api/public/l2/searchCategory/{name}", "Travel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId").value(1L));
    }

    @Test
    void searchCategory_returnsEmptyList_whenInvalidNameProvided() throws Exception {
        mockMvc.perform(get("/api/public/l2/searchCategory/{name}", "Music"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void getCategoryNames_returnsAllCategoryNames() throws Exception {
        mockMvc.perform(get("/api/public/l2/categoryNames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("Travel"))
                .andExpect(jsonPath("$[1]").value("Sport"));
    }

    @Test
    void getCategoryNames_returnsEmptyList_whenNoCategoriesPresent() throws Exception {
        ReflectionTestUtils.setField(
                categoryControllerL2,
                "categories",
                new ArrayList<CategoryL2>()
        );

        mockMvc.perform(get("/api/public/l2/categoryNames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}