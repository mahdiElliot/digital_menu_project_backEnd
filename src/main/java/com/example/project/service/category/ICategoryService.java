package com.example.project.service.category;

import com.example.project.model.category.Category;
import com.example.project.model.category.CategoryDTO;
import com.example.project.service.IService;

import java.util.List;

public interface ICategoryService extends IService<Category, CategoryDTO, Long> {
    List<CategoryDTO> findAllByBusinessId(Long id);
}
