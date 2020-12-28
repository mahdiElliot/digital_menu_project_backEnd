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
                .map(Zone::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ZoneDTO findByName(String name) {
        return null;
    }

    @Override
    public ZoneDTO findById(Long id) {
        if (id == null) return new ZoneDTO();
        return zoneRepository.findById(id)
                .map(Zone::convertToDTO).orElse(new ZoneDTO());
    }

    @Override
    public ZoneDTO delete(Long id) {
        if (id == null) return new ZoneDTO();
        Optional<Zone> menu = zoneRepository.findById(id);
        if (menu.isPresent()) {
            zoneRepository.deleteById(id);
            return menu.get().convertToDTO();
        }
        return new ZoneDTO();
    }

    @Override
    public ZoneDTO save(Zone zone) {
        return zoneRepository.save(zone).convertToDTO();
    }
}
