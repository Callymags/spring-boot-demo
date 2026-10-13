package com.example.demo.l4.repository;

// STEP 1: Create CategoryRepositoryL4 interface
// STEP 2: Extend JpaRepository for database operations
// STEP 3: Use inherited findById method

import com.example.demo.l4.model.CategoryL4;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoryL4 extends JpaRepository<CategoryL4, Long> {
}