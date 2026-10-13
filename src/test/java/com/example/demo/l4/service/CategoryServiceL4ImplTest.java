package com.example.demo.l4.service;

import com.example.demo.l4.model.CategoryL4;
import com.example.demo.l4.repository.CategoryRepositoryL4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceL4ImplTest {

    private CategoryRepositoryL4 categoryRepositoryL4;
    private CategoryServiceL4Impl categoryServiceL4;

    @BeforeEach
    void setUp() {
        categoryRepositoryL4 = mock(CategoryRepositoryL4.class);
        categoryServiceL4 = new CategoryServiceL4Impl(categoryRepositoryL4);
    }

    @Test
    void getCategoryById_returnsCategory_whenIdExists() {
        CategoryL4 category = new CategoryL4(1L, "Travel", "Travel Desc");
        when(categoryRepositoryL4.findById(1L)).thenReturn(Optional.of(category));

        Optional<CategoryL4> result = categoryServiceL4.getCategoryById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getCategoryId());
        assertEquals("Travel", result.get().getCategoryName());
        assertEquals("Travel Desc", result.get().getCategoryDesc());
        verify(categoryRepositoryL4).findById(1L);
    }

    @Test
    void getCategoryById_returnsEmpty_whenIdDoesNotExist() {
        when(categoryRepositoryL4.findById(99L)).thenReturn(Optional.empty());
        Optional<CategoryL4> result = categoryServiceL4.getCategoryById(99L);

        assertTrue(result.isEmpty());
        verify(categoryRepositoryL4).findById(99L);
    }
}