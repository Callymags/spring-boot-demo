package com.example.demo.l3.service;

import com.example.demo.l3.model.CategoryL3;

import java.util.Optional;

// STEP 1: Create CategoryServiceL3 interface
// STEP 2: Define getCategoryById service method
// STEP 3: Define addCategory service method
// STEP 4: Define updateCategory service method

public interface CategoryServiceL3 {
    Optional<CategoryL3> getCategoryById(Long id);

    String addCategory(CategoryL3 category);

    CategoryL3 updateCategory(Long id, CategoryL3 updatedCategory);
}
