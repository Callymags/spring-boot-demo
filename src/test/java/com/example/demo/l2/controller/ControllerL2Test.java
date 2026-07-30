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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        testCategories.add(new CategoryL2(3L, "Travel", "Travel Other"));

        ReflectionTestUtils.setField(categoryControllerL2, "categories", testCategories);
    }

    @Test
    void searchCategory_returnsList_whenValidNameProvided() throws Exception{
        mockMvc.perform(get("/api/public/l2/searchCategory/{name}", "Travel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].categoryName").value("Travel"));
    }

    // TESTS TO COMPLETE

    @Test
    void searchCategory_returnsEmpty_whenInvalidNameProvided() throws Exception{
        mockMvc.perform(get("/api/public/l2/searchCategory/{name}", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void getCategoryNames_returnsList_whenCatPresent() throws Exception {
        mockMvc.perform(get("/api/public/l2/getCategoryNames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void getCategoryNames_returnsEmptyList_whenCatNotPresent() throws Exception {
        ReflectionTestUtils.setField(categoryControllerL2, "categories", new ArrayList<CategoryControllerL2>());

        mockMvc.perform(get("/api/public/l2/getCategoryNames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

    }

    @Test
    void countCategoriesByName_returnsCorrectCount_whenNameExists() throws Exception {
        mockMvc.perform(get(
                        "/api/public/l2/countCategoriesByName/{name}",
                        "Travel"
                ))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    void countCategoriesByName_returnsZero_whenNameDoesNotExist() throws Exception {
        mockMvc.perform(get(
                        "/api/public/l2/countCategoriesByName/{name}",
                        "Music"
                ))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }
}