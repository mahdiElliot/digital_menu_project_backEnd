package com.example.project.service.paymethod;

import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.repositories.paymethod.PayMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PayMethodService implements IPayMethodService {
    private final PayMethodRepository payMethodRepository;

    @Autowired
    public PayMethodService(PayMethodRepository payMethodRepository) {
        this.payMethodRepository = payMethodRepository;
    }

    @Override
    public List<PayMethodDTO> findAll() {
        return ((List<PayMethod>) payMethodRepository.findAll())
                .stream()
                .map(PayMethod::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PayMethodDTO findByName(String name) {
        if (name == null) return null;
        return payMethodRepository.findByName(name)
                .map(PayMethod::convertToDTO).orElse(null);
    }

    @Override
    public PayMethodDTO findById(Long id) {
        if (id == null) return null;
        return payMethodRepository.findById(id)
                .map(PayMethod::convertToDTO).orElse(null);
    }

    @Override
    public PayMethodDTO delete(Long id) {
        if (id == null) return null;
        Optional<PayMethod> menu = payMethodRepository.findById(id);
        if (menu.isPresent()) {
            payMethodRepository.deleteById(id);
            return menu.get().convertToDTO();
        }
        return null;
    }

    @Override
    public PayMethodDTO save(PayMethod payMethod) {
        return payMethodRepository.save(payMethod).convertToDTO();
    }
}
