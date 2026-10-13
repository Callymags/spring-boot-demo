package com.example.demo.l4.controller;

// STEP 1: Create CategoryControllerL4 as REST controller
// STEP 2: Inject CategoryServiceL4 using constructor injection
// STEP 3: Create GET endpoint to retrieve category by id
// STEP 4: Delegate GET request to service layer

import com.example.demo.l4.model.CategoryL4;
import com.example.demo.l4.service.CategoryServiceL4;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CategoryControllerL4 {

    private final CategoryServiceL4 categoryServiceL4;

    public CategoryControllerL4(CategoryServiceL4 categoryServiceL4) {
        this.categoryServiceL4 = categoryServiceL4;
    }

    @GetMapping("/api/public/l4/getCategory/{id}")
    public Optional<CategoryL4> getCategory(@PathVariable Long id) {
        return categoryServiceL4.getCategoryById(id);
    }
}
