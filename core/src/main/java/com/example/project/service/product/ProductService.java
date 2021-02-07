package com.example.project.service.product;

import com.example.project.model.extra.Extra;
import com.example.project.model.product.Product;
import com.example.project.model.product.ProductDTO;
import com.example.project.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
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
                .map(Product::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findByName(String name) {
        if (name == null) return null;
        return repository.findByName(name)
                .map(Product::convertToDTO).orElse(null);
    }

    @Override
    public ProductDTO findById(Long id) {
        if (id == null) return null;
        return repository.findById(id)
                .map(Product::convertToDTO).orElse(null);
    }

    @Override
    public Product findProductById(Long id) {
        if (id == null) return null;
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public ProductDTO delete(Long id) {
        if (id == null) return null;
        Optional<Product> item = repository.findById(id);
        if (item.isPresent()) {
            repository.delete(id);
            return item.get().convertToDTO();
        }
        return null;
    }

    @Override
    public ProductDTO save(Product product) {
        return repository.save(product).convertToDTO();
    }

    @Override
    public List<ProductDTO> findAllByCategoryId(Long id) {
        return (repository.findAllByCategoryId(id))
                .stream()
                .map(Product::convertToDTO).collect(Collectors.toList());
    }
}
