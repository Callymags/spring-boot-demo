package com.example.demo.controller;

import com.example.demo.model.CategoryL1;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// STEP 1: Create an in-memory list to act as fake data for get request
// STEP 2: Create a get endpoint to get all categories.
// STEP 3: Create a get endpoint to get a specific category.
// STEP 4: Create a POST endpoint to add a category to the list.
// STEP 5: Create a DELETE endpoint to remove a category from the list. Print object to terminal before deletion.
// STEP 6: Create an Update endpoint to update entire existing category
// STEP 7: Create a PATCH endpoint to partially update existing category
// STEP 8: Create a DELETE endpoint to delete existing category

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
   public String deleteCategory(@PathVariable Long id) {
     boolean removeCategory = categories.removeIf(category -> category.getCategoryId().equals(id));

     if(removeCategory){
         return "Category removed";
     } else {
         return "Category not found";
     }
   }

}
