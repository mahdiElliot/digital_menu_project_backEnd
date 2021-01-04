package com.example.project.service.business;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.model.location.Location;
import com.example.project.repositories.business.BusinessRepository;
import com.example.project.repositories.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService implements IBusinessService {

    private final BusinessRepository businessRepository;

    @Autowired
    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public List<BusinessDTO> findAll() {
        return ((List<Business>) businessRepository.findAll())
                .stream()
                .map(Business::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BusinessDTO findByName(String name) {
        if (name == null) return null;
        return businessRepository.findByName(name)
                .map(Business::convertToDTO).orElse(null);
    }

    @Override
    public BusinessDTO findById(Long id) {
        if (id == null) return null;
        return businessRepository.findById(id)
                .map(Business::convertToDTO).orElse(null);
    }

    @Override
    public BusinessDTO delete(Long id) {
        BusinessDTO businessDTO = findById(id);
        if (businessDTO == null) return null;
        businessRepository.deleteById(id);
        return businessDTO;
    }

    @Override
    public BusinessDTO save(Business business) {
        return businessRepository.save(business).convertToDTO();
    }
}
