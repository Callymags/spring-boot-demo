package com.example.demo.l3.model;

import lombok.*;

// STEP 1: Create CategoryL3 model with id, name, and description fields
// STEP 2: Use Lombok to generate getters and setters
// STEP 3: Use Lombok to generate no-args and all-args constructors
// STEP 4: Use Lombok to generate toString()

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryL3 {
    private Long categoryId;
    private String categoryName;
    private String categoryDesc;
}
