package com.example.project.service.business;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.repositories.business.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BusinessDTO findByName(String name) {
        return null;
    }

    @Override
    public BusinessDTO findById(Long id) {
        return businessRepository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public BusinessDTO delete(Long id) {
        Optional<Business> menu = businessRepository.findById(id);
        if (menu.isPresent()) {
            businessRepository.deleteById(id);
            return convertToDTO(menu.get());
        }
        return null;
    }

    @Override
    public BusinessDTO save(Business business) {
        return convertToDTO(businessRepository.save(business));
    }

    @Override
    public BusinessDTO convertToDTO(Business business) {
        return new BusinessDTO(
                business.getId(),
                business.getName(),
                business.getServiceFee(),
                business.getTax(),
                business.getLogo(),
                business.getEnabled(),
                business.getMenus(),
                business.getOrders(),
                business.getPayMethods()
        );
    }
}
