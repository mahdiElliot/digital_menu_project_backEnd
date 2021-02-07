package com.example.project.service.purchase;

import com.example.project.model.purchase.Purchase;
import com.example.project.model.purchase.PurchaseDTO;
import com.example.project.repositories.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService implements IPurchaseService {

    private final PurchaseRepository repository;

    @Autowired
    public PurchaseService(PurchaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PurchaseDTO> findAll() {
        return ((List<Purchase>) repository.findAll())
                .stream()
                .map(Purchase::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PurchaseDTO findByName(String name) {
        if (name == null) return null;
        return repository.findByName(name)
                .map(Purchase::convertToDTO).orElse(null);
    }

    @Override
    public PurchaseDTO findById(Long id) {
        if (id == null) return null;
        return repository.findById(id)
                .map(Purchase::convertToDTO).orElse(null);
    }

    @Override
    public PurchaseDTO delete(Long id) {
        if (id == null) return null;
        Optional<Purchase> item = repository.findById(id);
        if (item.isPresent()) {
            repository.delete(id);
            return item.get().convertToDTO();
        }
        return null;
    }

    @Override
    public PurchaseDTO save(Purchase purchase) {
        return repository.save(purchase).convertToDTO();
    }
}
