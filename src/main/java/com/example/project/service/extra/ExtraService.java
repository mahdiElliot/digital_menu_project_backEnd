package com.example.project.service.extra;

import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.repositories.extra.ExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExtraService implements IExtraService {

    private final ExtraRepository extraRepository;

    @Autowired
    public ExtraService(ExtraRepository extraRepository) {
        this.extraRepository = extraRepository;
    }

    @Override
    public List<ExtraDTO> findAll() {
        return ((List<Extra>) extraRepository.findAll())
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ExtraDTO findByName(String name) {
        return convertToDTO(extraRepository.findByName(name));
    }

    @Override
    public ExtraDTO findById(Long id) {
        return extraRepository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public ExtraDTO delete(Long id) {
        Optional<Extra> menu = extraRepository.findById(id);
        if (menu.isPresent()) {
            extraRepository.deleteById(id);
            return convertToDTO(menu.get());
        }
        return null;
    }

    @Override
    public ExtraDTO save(Extra extra) {
        return convertToDTO(extraRepository.save(extra));
    }

    @Override
    public ExtraDTO convertToDTO(Extra extra) {
        return extra.convertToDTO();
    }
}
