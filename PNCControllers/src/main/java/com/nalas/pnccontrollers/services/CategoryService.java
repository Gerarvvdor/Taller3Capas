package com.nalas.pnccontrollers.services;

import com.nalas.pnccontrollers.domain.dtos.SaveCategoryDTO;
import com.nalas.pnccontrollers.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    Category findCategoryById(String id);
    void deleteById(String id);
    void save(SaveCategoryDTO info);
}
