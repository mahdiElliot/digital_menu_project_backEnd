package com.example.project.service.zone;

import com.example.project.model.zone.Zone;
import com.example.project.model.zone.ZoneDTO;
import com.example.project.repositories.zone.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZoneService implements IZoneService {

    private final ZoneRepository zoneRepository;

    @Autowired
    public ZoneService(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public List<ZoneDTO> findAll() {
        return ((List<Zone>) zoneRepository.findAll())
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ZoneDTO findByName(String name) {
        return null;
    }

    @Override
    public ZoneDTO findById(Long id) {
        return zoneRepository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public ZoneDTO delete(Long id) {
        Optional<Zone> menu = zoneRepository.findById(id);
        if (menu.isPresent()) {
            zoneRepository.deleteById(id);
            return convertToDTO(menu.get());
        }
        return null;
    }

    @Override
    public ZoneDTO save(Zone zone) {
        return convertToDTO(zoneRepository.save(zone));
    }

    @Override
    public ZoneDTO convertToDTO(Zone zone) {
        return zone.convertToDTO();
    }
}
