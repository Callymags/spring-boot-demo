package com.example.demo.controller;

import com.example.demo.model.CategoryL1;
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
// Uses: removeIf lambda

// STEP 8: Create a GET endpoint to search categories by exact name.
// Uses: stream(), filter(), toList()

// STEP 9: Create a GET endpoint to return only category names.
// Uses: stream(), map(), toList()

// STEP 10: Create a GET endpoint to count categories by name.
// Uses: stream(), filter(), count()

// STEP 11: Refactor one existing endpoint to use stream(), findFirst(), orElse(null).
// Suggestion: use this on getCategory later, after practising the for loop version.

// STEP 12: Create a GET endpoint to check whether a category exists by id.
// Uses: stream(), anyMatch()

// STEP 13: Create a GET endpoint to return categories sorted by name.
// Uses: stream(), sorted(), Comparator

@RestController
public class CategoryControllerL1 {
   private List<CategoryL1> categories = new ArrayList<>();

   @GetMapping("/api/public/l1/getCategories")
    public List<CategoryL1> getCategories(){
       return categories;
   }

   @GetMapping("/api/public/l1/getCategory/{id}")
    public CategoryL1 getCategory(@PathVariable Long id){
       for(CategoryL1 category: categories){
           if(category.getCategoryId().equals(id)){
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
   public String deleteCategory(@PathVariable Long id){
      boolean catRemoved = categories.removeIf(category -> category.getCategoryId().equals(id));

      if(catRemoved){
          return "Category removed";
      } else {
          return "Category not found";
      }
   }

   @GetMapping("/api/public/l1/searchCategory/{name}")
   public List<CategoryL1> searchCategory(@PathVariable String name){
       return categories.stream().filter(
               category -> category.getCategoryName().equals(name)).toList();
   }

    @GetMapping("/api/public/l1/categoryNames")
    public List<String> getCategoryNames() {
        return categories.stream()
                .map(category -> category.getCategoryName())
                .toList();
    }

}
