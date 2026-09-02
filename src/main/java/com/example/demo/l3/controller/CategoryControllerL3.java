package com.example.demo.l3.controller;

import com.example.demo.l3.model.CategoryL3;
import com.example.demo.l3.service.CategoryServiceL3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CategoryControllerL3 {

    @Autowired
    CategoryServiceL3 categoryServiceL3;

    @GetMapping("/api/public/l3/getCategory/{id}")
    public Optional<CategoryL3> getCategory(@PathVariable Long id) {
        return categoryServiceL3.getCategoryById(id);
    }
}
