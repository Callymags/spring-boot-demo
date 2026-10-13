package com.example.demo.l4.repository;

import com.example.demo.l4.model.CategoryL4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.sql.init.mode=never",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class CategoryRepositoryL4Test {

    @Autowired
    private CategoryRepositoryL4 categoryRepositoryL4;

    @Test
    void findById_returnsCategory_whenIdExists() {

        CategoryL4 category =
                new CategoryL4(null, "Travel", "Travel Desc");

        CategoryL4 savedCategory =
                categoryRepositoryL4.save(category);

        Optional<CategoryL4> result =
                categoryRepositoryL4.findById(savedCategory.getCategoryId());

        assertTrue(result.isPresent());
        assertEquals(savedCategory.getCategoryId(), result.get().getCategoryId());
        assertEquals("Travel", result.get().getCategoryName());
        assertEquals("Travel Desc", result.get().getCategoryDesc());
    }

    @Test
    void findById_returnsEmpty_whenIdDoesNotExist() {

        Optional<CategoryL4> result =
                categoryRepositoryL4.findById(99L);

        assertTrue(result.isEmpty());
    }
}