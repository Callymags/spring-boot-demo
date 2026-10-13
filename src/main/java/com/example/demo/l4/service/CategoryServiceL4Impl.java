package com.example.demo.l4.service;

// STEP 1: Implement CategoryServiceL4
// STEP 2: Add @Service annotation
// STEP 3: Inject CategoryRepositoryL4 using constructor injection
// STEP 4: Delegate getCategoryById to the repository

import com.example.demo.l4.model.CategoryL4;
import com.example.demo.l4.repository.CategoryRepositoryL4;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceL4Impl implements CategoryServiceL4 {

    private final CategoryRepositoryL4 categoryRepositoryL4;

    public CategoryServiceL4Impl(CategoryRepositoryL4 categoryRepositoryL4) {
        this.categoryRepositoryL4 = categoryRepositoryL4;
    }

    @Override
    public Optional<CategoryL4> getCategoryById(Long id) {
        return categoryRepositoryL4.findById(id);
    }
}
