package com.example.demo.l3.controller;

import com.example.demo.l3.model.CategoryL3;
import com.example.demo.l3.service.CategoryServiceL3;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// STEP 1: Create CategoryControllerL3 as a REST controller
// STEP 2: Inject CategoryServiceL3 using constructor injection
// STEP 3: Create GET endpoint to get category by id with logic in service layer
// STEP 4: Create POST endpoint to add category

@RestController
public class CategoryControllerL3 {

    private final CategoryServiceL3 categoryServiceL3;

    public CategoryControllerL3(CategoryServiceL3 categoryServiceL3) {
        this.categoryServiceL3 = categoryServiceL3;
    }

    @GetMapping("/api/public/l3/getCategory/{id}")
    public Optional<CategoryL3> getCategory(@PathVariable Long id) {
        return categoryServiceL3.getCategoryById(id);
    }

    @PostMapping("/api/public/l3/addCategory")
    public String addCategory(@RequestBody CategoryL3 category) {
        return categoryServiceL3.addCategory(category);
    }
}
