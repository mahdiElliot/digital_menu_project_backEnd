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
                .map(SubOption::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SubOptionDTO findByName(String name) {
        if (name == null) return null;
        return repository.findByName(name)
                .map(SubOption::convertToDTO).orElse(null);
    }

    @Override
    public SubOptionDTO findById(Long id) {
        if (id == null) return null;
        return repository.findById(id)
                .map(SubOption::convertToDTO).orElse(null);
    }

    @Override
    public SubOptionDTO delete(Long id) {
        if (id == null) return null;
        Optional<SubOption> item = repository.findById(id);
        if (item.isPresent()) {
            repository.deleteById(id);
            return item.get().convertToDTO();
        }
        return null;
    }

    @Override
    public SubOptionDTO save(SubOption subOption) {
        return repository.save(subOption).convertToDTO();
    }

    @Override
    public List<SubOptionDTO> findAllByOptionId(Long id) {
        return (repository.findAllByOptionId(id))
                .stream()
                .map(SubOption::convertToDTO).collect(Collectors.toList());
    }
}
