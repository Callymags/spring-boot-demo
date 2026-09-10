package com.example.demo.l3.service;

import com.example.demo.l3.model.CategoryL3;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// STEP 1: Create CategoryServiceL3Impl and implement CategoryServiceL3
// STEP 2: Add @Service annotation for Spring bean management
// STEP 3: Create in-memory category list as fake data
// STEP 4: Override getCategoryById
// STEP 5: Use stream, filter, and findFirst to find category by id
// STEP 5: Create addCategory method
// STEP 6: Create updateCategory method

@Service
public class CategoryServiceL3Impl implements CategoryServiceL3{
    private List<CategoryL3> categories = new ArrayList<>(
            List.of(
                    new CategoryL3(1L, "Travel", "Travel Desc"),
                    new CategoryL3(2L, "Sport", "Sport Desc"),
                    new CategoryL3(3L, "Food", "Food Desc")
            )
    );

    @Override
    public Optional<CategoryL3> getCategoryById(Long id) {
        return categories.stream()
                .filter(category -> category.getCategoryId().equals(id))
                .findFirst();
    }

    @Override
    public String addCategory(CategoryL3 category) {
        categories.add(category);
        return "Category added: " + category;
    }

    @Override
    public CategoryL3 updateCategory(Long id, CategoryL3 updatedCategory) {
        for (CategoryL3 category : categories) {
            if (category.getCategoryId().equals(id)) {
                category.setCategoryName(updatedCategory.getCategoryName());
                category.setCategoryDesc(updatedCategory.getCategoryDesc());

                return category;
            }
        }
        return null;
    }


}
