package com.example.project.service.product;

import com.example.project.model.product.Product;
import com.example.project.model.product.ProductDTO;
import com.example.project.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductDTO> findAll() {
        return ((List<Product>) repository.findAll())
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findByName(String name) {
        return convertToDTO(repository.findByName(name));
    }

    @Override
    public ProductDTO findById(Long id) {
        return repository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public ProductDTO delete(Long id) {
        Optional<Product> item = repository.findById(id);
        if (item.isPresent()) {
            repository.deleteById(id);
            return convertToDTO(item.get());
        }
        return null;
    }

    @Override
    public ProductDTO save(Product product) {
        return convertToDTO(repository.save(product));
    }

    @Override
    public ProductDTO convertToDTO(Product product) {
        return product.convertToDTO();
    }
}
