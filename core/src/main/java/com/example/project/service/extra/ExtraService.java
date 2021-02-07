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
                .map(Extra::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ExtraDTO findByName(String name) {
        if (name == null) return null;
        return extraRepository.findByName(name)
                .map(Extra::convertToDTO).orElse(null);
    }

    @Override
    public ExtraDTO findById(Long id) {
        if (id == null) return null;
        return extraRepository.findById(id)
                .map(Extra::convertToDTO).orElse(null);
    }

    @Override
    public ExtraDTO delete(Long id) {
        if (id == null) return null;
        Optional<Extra> menu = extraRepository.findById(id);
        if (menu.isPresent()) {
            extraRepository.delete(id);
            return menu.get().convertToDTO();
        }
        return null;
    }

    @Override
    public ExtraDTO save(Extra extra) {
        return extraRepository.save(extra).convertToDTO();
    }

    @Override
    public List<ExtraDTO> findExtrasByBusinessId(Long id) {
        return ((List<Extra>) extraRepository.findExtrasByBusinessId(id))
                .stream()
                .map(Extra::convertToDTO).collect(Collectors.toList());
    }
}
