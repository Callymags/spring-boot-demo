package com.example.demo.l3.service;

import com.example.demo.l3.model.CategoryL3;

import java.util.Optional;

public interface CategoryServiceL3 {
    Optional<CategoryL3> getCategoryById(Long id);
}
