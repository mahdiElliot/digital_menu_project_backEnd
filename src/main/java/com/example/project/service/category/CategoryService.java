package com.example.project.service.category;

import com.example.project.model.category.Category;
import com.example.project.model.category.CategoryDTO;
import com.example.project.repositories.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return ((List<Category>) categoryRepository.findAll())
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findAllByBusinessId(Long id) {
        return (categoryRepository.findAllByBusinessId(id))
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findByName(String name) {
        return convertToDTO(categoryRepository.findByName(name));
    }

    @Override
    public CategoryDTO findById(Long id) {
        return categoryRepository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public CategoryDTO delete(Long id) {
        Optional<Category> menu = categoryRepository.findById(id);
        if (menu.isPresent()) {
            categoryRepository.deleteById(id);
            return convertToDTO(menu.get());
        }
        return null;
    }

    @Override
    public CategoryDTO save(Category category) {
        return convertToDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO convertToDTO(Category category) {
        return null;
    }

}
