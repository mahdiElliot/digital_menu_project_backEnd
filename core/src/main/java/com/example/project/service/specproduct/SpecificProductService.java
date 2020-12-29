package com.example.project.service.specproduct;

import com.example.project.model.specproduct.SpecificProduct;
import com.example.project.model.specproduct.SpecificProductDTO;
import com.example.project.repositories.specproduct.SpecificProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecificProductService implements ISpecificProductService {

    private final SpecificProductRepository repository;

    @Autowired
    public SpecificProductService(SpecificProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SpecificProductDTO> findAll() {
        return ((List<SpecificProduct>) repository.findAll())
                .stream()
                .map(SpecificProduct::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SpecificProductDTO findByName(String name) {
        if (name == null) return new SpecificProductDTO();
        return repository.findByName(name).convertToDTO();
    }

    @Override
    public SpecificProductDTO findById(Long id) {
        if (id == null) return new SpecificProductDTO();
        return repository.findById(id)
                .map(SpecificProduct::convertToDTO).orElse(new SpecificProductDTO());
    }

    @Override
    public SpecificProductDTO delete(Long id) {
        if (id == null) return new SpecificProductDTO();
        Optional<SpecificProduct> item = repository.findById(id);
        if (item.isPresent()) {
            repository.deleteById(id);
            return item.get().convertToDTO();
        }
        return new SpecificProductDTO();
    }

    @Override
    public SpecificProductDTO save(SpecificProduct specificProduct) {
        return repository.save(specificProduct).convertToDTO();
    }
}