package com.nalas.pnccontrollers.controllers;

import com.nalas.pnccontrollers.domain.dtos.GeneralResponse;
import com.nalas.pnccontrollers.domain.dtos.SaveCategoryDTO;
import com.nalas.pnccontrollers.domain.entities.Category;
import com.nalas.pnccontrollers.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Get all categories endpoint
    @GetMapping("/")
    public ResponseEntity<GeneralResponse> findAllCategory() {
        return GeneralResponse.builder()
                .data(categoryService.findAllCategories())
                .build();
    }

    // Get category by id endpoint
    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> findCategoryById(@PathVariable String id) {
        Category category = categoryService.findCategoryById(id);

        if (category == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return GeneralResponse.builder()
                .data(category)
                .build();
    }

    // Save category endpoint
    @PostMapping("/save")
    public ResponseEntity<GeneralResponse> saveCategory(@RequestBody @Valid SaveCategoryDTO info) {
        categoryService.save(info);

        return GeneralResponse.builder()
                .message("Category saved!")
                .status(HttpStatus.CREATED)
                .build();
    }

    // Delete category endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteCategoryById(@PathVariable String id) {
        Category category = categoryService.findCategoryById(id);

        if (category == null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        categoryService.deleteById(id);

        return GeneralResponse.builder()
                .status(HttpStatus.OK)
                .build();
    }
}
