package com.nalas.pnccontrollers.services.implementations;

import com.nalas.pnccontrollers.domain.dtos.SaveCategoryDTO;
import com.nalas.pnccontrollers.domain.entities.Category;
import com.nalas.pnccontrollers.repositories.CategoryRepository;
import com.nalas.pnccontrollers.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void save(SaveCategoryDTO info) {
        Category category = this.findCategoryById(info.getCode());

        if (category == null) {
            category = new Category();
        }

        category.setName(info.getName());
        category.setCode(info.getCode());

        this.categoryRepository.save(category);
    }
}
