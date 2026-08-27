package com.example.demo.l2.controller;

// STEP 1: Create an in-memory list to act as fake data.
// STEP 2: Create a GET endpoint to search categories by exact name.
// STEP 3: Return only category names using map
// STEP 4: Count categories by name using count
// STEP 5: Return boolean if categoryId exists using anyMatch
// STEP 6: Get category by id using findFirst
// STEP 7: Sort categories by name

import com.example.demo.l2.model.CategoryL2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryControllerL2 {
    private List<CategoryL2> categories = new ArrayList<>(
            List.of(
                    new CategoryL2(1L, "Travel", "Travel Desc"),
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

    @GetMapping("/api/public/l2/categoryExists/{id}")
    public boolean categoryExists(@PathVariable Long id) {
        return categories.stream()
                .anyMatch(category -> category.getCategoryId().equals(id));
    }

    @GetMapping("/api/public/l2/getCategory/{id}")
    public Optional<CategoryL2> getCategory(@PathVariable Long id) {
        return categories.stream()
                .filter(category -> category.getCategoryId().equals(id))
                .findFirst();
    }

    @GetMapping("/api/public/l2/getCategoriesSortedByName")
    public List<CategoryL2> getCategoriesSortedByName() {
        return categories.stream()
                .sorted(Comparator.comparing(CategoryL2::getCategoryName))
                .toList();
    }
}
