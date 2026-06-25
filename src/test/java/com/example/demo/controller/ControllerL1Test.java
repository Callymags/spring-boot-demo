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
// STEP 7: Create a test to cover patchCategory endpoint
// STEP 6: Create a test to cover deleteCategory endpoint

@WebMvcTest
public class ControllerL1Test {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CategoryControllerL1 categoryControllerL1;

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
    void getCategories_returnsCat_whenListPopulated() throws Exception {
        mockMvc.perform(get("/api/public/l1/getCategories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].categoryName").value("Travel"))
                .andExpect(jsonPath("$[1].categoryName").value("Sport"));
    }

    @Test
    void getCategory_returnsCat_whenValidIdPresent() throws Exception {
        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1L))
                .andExpect(jsonPath("$.categoryName").value("Travel"));
    }

    @Test
    void getCategory_returnsNull_whenInvalidIdPresent() throws Exception {
        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 99L))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }


    @Test
    void addCategory_returnsString_whenValidCatAdded() throws Exception {
        CategoryL1 category = new CategoryL1(3L, "Food", "Test");

        mockMvc.perform(post("/api/public/l1/addCategory").contentType("application/json")
                .content(toJson(category))).andExpect(status().isOk())
                .andExpect(content().string(containsString("Category added")));

        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 3L))
                .andExpect(status().isOk()).andExpect(jsonPath("$.categoryName").value("Food"));
    }

    @Test
    void updateCategory_returnsString_whenValidCatProvided() throws Exception {
        CategoryL1 updatedCat = new CategoryL1(1L, "Activity", "Test Update");

        mockMvc.perform(put("/api/public/l1/updateCategory/{id}", 1L).contentType("application/json")
                .content(toJson(updatedCat))).andExpect(status().isOk())
                .andExpect(content().string(containsString("Category updated:")));

        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").value("Activity"));
    }

    @Test
    void updateCategory_returnsEmptyString_whenInvalidCatProvided() throws Exception {
        CategoryL1 updatedCat = new CategoryL1(5L, "Activity", "Test");

        mockMvc.perform(put("/api/public/l1/updateCategory/{id}", 5L)
                .contentType("application/json").content(toJson(updatedCat)))
                .andExpect(status().isOk()).andExpect(content().string(containsString("")));
    }

    @Test
    void patchCategory_returnsString_whenValidIdAndCatNameProvided() throws Exception {
        CategoryL1 patchedCat = new CategoryL1(1L, "Activity", null);

        mockMvc.perform(patch("/api/public/l1/patchCategory/{id}", 1L)
                .contentType("application/json").content(toJson(patchedCat)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Patched category")));

        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").value("Activity"));
    }

    @Test
    void patchCategory_returnsString_whenValidCatDescAndIdProvided() throws Exception {
        CategoryL1 patchedCat = new CategoryL1(1L, null, "Dummy Desc");

        mockMvc.perform(patch("/api/public/l1/patchCategory/{id}", 1L).contentType("application/json").content(toJson(patchedCat))
                ).andExpect(status().isOk()).andExpect(content().string(containsString("Patched category")));

        mockMvc.perform(get("/api/public/l1/getCategory/{id}", 1L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryDesc").value("Dummy Desc"));
    }

    @Test
    void patchCategory_returnsNull_whenInvalidIdProvided() throws Exception {
        CategoryL1 patchedCat = new CategoryL1(99L, "Patched Cat", "Patched Desc");

        mockMvc.perform(patch("/api/public/l1/patchCategory/{id}", 99L)
                .contentType("application/json").content(toJson(patchedCat)))
                .andExpect(status().isOk()).andExpect(content().string(""));
    }

    @Test
    void deleteCategory_returnsString_whenValidIdProvided() throws Exception {
        mockMvc.perform(delete("/api/public/l1/deleteCategory/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Category removed")));
    }

    @Test
    void deleteCategory_returnsString_whenInvalidIdProvided() throws Exception {
        mockMvc.perform(delete("/api/public/l1/deleteCategory/{id}", 99L))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Category not found")));
    }

    @Test
    void searchCategory_returnsList_whenValidNameProvided() throws Exception {
        mockMvc.perform(get("/api/public/l1/searchCategory/{name}", "Travel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].categoryName").value("Travel"));
    }

    @Test
    void searchCategory_returnsBlank_whenInvalidNameProvided() throws Exception {
        mockMvc.perform(get("/api/public/l1/searchCategory/{name}", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void getCategoryNames_returnsCategoryNames_whenCategoriesPresent() throws Exception {
        mockMvc.perform(get("/api/public/l1/getCategoryNames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("Travel"));
    }

    @Test
    void countCategoriesByName_returnsCount_whenNameExists() throws Exception {
        mockMvc.perform(get("/api/public/l1/countCategory/{name}", "Sport"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void countCategoriesByName_returnsZero_whenNameDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/public/l1/countCategory/{name}", "Music"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    private String toJson(CategoryL1 category) throws Exception {
        return objectMapper.writeValueAsString(category);
    }
}
