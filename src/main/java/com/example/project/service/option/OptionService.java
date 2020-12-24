package com.example.project.service.option;

import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.repositories.option.OptionRepository;
import com.example.project.service.option.IOptionService;
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
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OptionDTO findByName(String name) {
        return convertToDTO(repository.findByName(name));
    }

    @Override
    public OptionDTO findById(Long id) {
        return repository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public OptionDTO delete(Long id) {
        Optional<Option> item = repository.findById(id);
        if (item.isPresent()) {
            repository.deleteById(id);
            return convertToDTO(item.get());
        }
        return null;
    }

    @Override
    public OptionDTO save(Option option) {
        return convertToDTO(repository.save(option));
    }

    @Override
    public OptionDTO convertToDTO(Option option) {
        return option.convertToDTO();
    }
}
