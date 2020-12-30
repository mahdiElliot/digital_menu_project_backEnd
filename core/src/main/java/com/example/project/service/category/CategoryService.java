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
                .map(Category::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findAllByBusinessId(Long id) {
        return (categoryRepository.findAllByBusinessId(id))
                .stream()
                .map(Category::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findByName(String name) {
        if (name == null) return null;
        return categoryRepository.findByName(name).convertToDTO();
    }

    @Override
    public CategoryDTO findById(Long id) {
        if (id == null) return null;
        return categoryRepository.findById(id)
                .map(Category::convertToDTO).orElse(null);
    }

    @Override
    public CategoryDTO delete(Long id) {
        if (id == null) return null;
        Optional<Category> menu = categoryRepository.findById(id);
        if (menu.isPresent()) {
            categoryRepository.deleteById(id);
            return menu.get().convertToDTO();
        }
        return null;
    }

    @Override
    public CategoryDTO save(Category category) {
        return categoryRepository.save(category).convertToDTO();
    }

}
