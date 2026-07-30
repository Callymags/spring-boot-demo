package com.example.demo.l2.controller;

// STEP 1: Create an in-memory list to act as fake data.
// STEP 2: Create a GET endpoint to search categories by exact name.
// STEP 3: Return only category names using map
// STEP 4: Count categories by name using count

import com.example.demo.l1.model.CategoryL1;
import com.example.demo.l2.model.CategoryL2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryControllerL2 {
    private List<CategoryL2> categories = new ArrayList<>(
            List.of(
                    new CategoryL2(2L, "Travel", "Travel Desc"),
                    new CategoryL2(2L, "Sport", "Sport Desc"),
                    new CategoryL2(3L, "Travel", "Travel Other")
            )
    );

    @GetMapping("/api/public/l2/searchCategory/{name}")
    public List<CategoryL2> searchCategory(@PathVariable String name){
        return categories.stream().filter
                (category -> category.getCategoryName().equals(name)).toList();
    }

    @GetMapping("/api/public/l2/getCategoryNames")
    public List<String> getCategoryNames(){
        return categories.stream().map(CategoryL2::getCategoryName).toList();
    }

    @GetMapping("/api/public/l2/countCategoriesByName/{name}")
    public long countCategoriesByName(@PathVariable String name) {
        return categories.stream()
                .filter(category -> category.getCategoryName().equals(name))
                .count();
    }
}
