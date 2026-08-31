package com.example.demo.l1.controller;

import com.example.demo.l1.model.CategoryL1;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// STEP 1: Create an in-memory list to act as fake data for requests

// STEP 2: Create a GET endpoint to get all categories.
// Uses: basic List return

// STEP 3: Create a GET endpoint to get a specific category by id.
// Uses: for loop practice

// STEP 4: Create a POST endpoint to add a category to the list.
// Uses: @RequestBody

// STEP 5: Create a PUT endpoint to update an entire existing category.
// Uses: for loop practice

// STEP 6: Create a PATCH endpoint to partially update an existing category.
// Uses: null checks and conditional updates

// STEP 7: Create a DELETE endpoint to remove a category by id.

@RestController
public class CategoryControllerL1 {
   private List<CategoryL1> categories = new ArrayList<>();

   @GetMapping("/api/public/l1/getCategories")
   public List<CategoryL1> getCategories(){
       return categories;
   }

    @GetMapping("/api/public/l1/getCategory/{id}")
    public CategoryL1 getCategory(@PathVariable Long id) {
        for (CategoryL1 category : categories) {
            if (category.getCategoryId().equals(id)) {
                return category;
            }
        }
        return null;
    }

   @PostMapping("/api/public/l1/addCategory")
    public String addCategory(@RequestBody CategoryL1 category){
       categories.add(category);
       return "Category added: " + category;
   }

   @PutMapping("/api/public/l1/updateCategory/{id}")
   public String updateCategory(@RequestBody CategoryL1 updatedCat, @PathVariable Long id){
       for(CategoryL1 category:categories){
           if(category.getCategoryId().equals(id)){
               category.setCategoryName(updatedCat.getCategoryName());
               category.setCategoryDesc(updatedCat.getCategoryDesc());
               return "Category updated: " + category;
           }
       }
       return null;
   }

   @PatchMapping("/api/public/l1/patchCategory/{id}")
   public String patchCategory(@RequestBody CategoryL1 patchedCat, @PathVariable Long id){
       for (CategoryL1 category:categories){
           if(category.getCategoryId().equals(id)){
               if (patchedCat.getCategoryName() != null){
                   category.setCategoryName(patchedCat.getCategoryName());
               }

               if(patchedCat.getCategoryDesc() != null){
                   category.setCategoryDesc(patchedCat.getCategoryDesc());
               }
               return "Patched category: " + category;
           }
       }
       return null;
   }

    @DeleteMapping("/api/public/l1/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCategoryId().equals(id)) {
                CategoryL1 removedCategory = categories.remove(i);
                return "Category deleted: " + removedCategory;
            }
        }
        return null;
    }
}
