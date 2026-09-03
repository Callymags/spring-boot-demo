package com.example.demo.l3.service;

import com.example.demo.l3.model.CategoryL3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// STEP 1: Create CategoryServiceL3Impl directly without loading Spring
// STEP 2: Create controlled test category data in setUp()
// STEP 3: Inject test data into the service
// STEP 4: Test getCategoryById with a valid id
// STEP 5: Test getCategoryById with an invalid id

public class CategoryServiceL3ImplTest {

    private CategoryServiceL3Impl categoryServiceL3;

    @BeforeEach
    void setUp() {
        categoryServiceL3 = new CategoryServiceL3Impl();

        List<CategoryL3> testCategories = new ArrayList<>();
        testCategories.add(new CategoryL3(1L, "Travel", "Travel Desc"));
        testCategories.add(new CategoryL3(2L, "Sport", "Sport Desc"));
        testCategories.add(new CategoryL3(3L, "Food", "Food Desc"));
        ReflectionTestUtils.setField(categoryServiceL3, "categories", testCategories);
    }

    @Test
    void getCategoryById_returnsCategory_whenIdExists() {
        Optional<CategoryL3> result = categoryServiceL3.getCategoryById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getCategoryId());
        assertEquals("Travel", result.get().getCategoryName());
        assertEquals("Travel Desc", result.get().getCategoryDesc());
    }

    @Test
    void getCategoryById_returnsEmpty_whenIdDoesNotExist() {
        Optional<CategoryL3> result = categoryServiceL3.getCategoryById(99L);
        assertTrue(result.isEmpty());
    }
}
