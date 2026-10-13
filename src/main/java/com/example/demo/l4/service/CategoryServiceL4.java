package com.example.demo.l4.service;

// STEP 1: Create CategoryServiceL4 interface
// STEP 2: Define getCategoryById method

import com.example.demo.l4.model.CategoryL4;

import java.util.Optional;

public interface CategoryServiceL4 {

    Optional<CategoryL4> getCategoryById(Long id);
}
