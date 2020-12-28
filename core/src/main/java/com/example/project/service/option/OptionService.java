package com.example.project.service.option;

import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.repositories.option.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OptionService implements IOptionService {

    private final OptionRepository repository;

    @Autowired
    public OptionService(OptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OptionDTO> findAll() {
        return ((List<Option>) repository.findAll())
                .stream()
                .map(Option::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OptionDTO findByName(String name) {
        if (name == null) return null;
        return repository.findByName(name).convertToDTO();
    }

    @Override
    public OptionDTO findById(Long id) {
        if (id == null) return null;
        return repository.findById(id)
                .map(Option::convertToDTO).orElse(null);
    }

    @Override
    public OptionDTO delete(Long id) {
        if (id == null) return null;
        Optional<Option> item = repository.findById(id);
        if (item.isPresent()) {
            repository.deleteById(id);
            return item.get().convertToDTO();
        }
        return null;
    }

    @Override
    public OptionDTO save(Option option) {
        return repository.save(option).convertToDTO();
    }
}
