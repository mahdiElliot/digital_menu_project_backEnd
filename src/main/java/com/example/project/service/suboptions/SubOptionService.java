package com.example.project.service.suboptions;

import com.example.project.model.suboptions.SubOption;
import com.example.project.model.suboptions.SubOptionDTO;
import com.example.project.repositories.suboptions.SubOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubOptionService implements ISubOptionService {

    private final SubOptionRepository repository;

    @Autowired
    public SubOptionService(SubOptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SubOptionDTO> findAll() {
        return ((List<SubOption>) repository.findAll())
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SubOptionDTO findByName(String name) {
        return convertToDTO(repository.findByName(name));
    }

    @Override
    public SubOptionDTO findById(Long id) {
        return repository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public SubOptionDTO delete(Long id) {
        Optional<SubOption> item = repository.findById(id);
        if (item.isPresent()) {
            repository.deleteById(id);
            return convertToDTO(item.get());
        }
        return null;
    }

    @Override
    public SubOptionDTO save(SubOption subOption) {
        return convertToDTO(repository.save(subOption));
    }

    @Override
    public List<SubOptionDTO> findAllByOptionId(Long id) {
        return (repository.findAllByOptionId(id))
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SubOptionDTO convertToDTO(SubOption subOption) {
        return subOption.convertToDTO();
    }
}
