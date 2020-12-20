package com.example.project.service.business;

import com.example.project.model.DTO;
import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.repositories.business.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService implements IBusinessService {

    private final BusinessRepository businessRepository;

    @Autowired
    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public List<DTO> findAll() {
        return null;
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
    public BusinessDTO save(Business business) {
        return convertToDTO(businessRepository.save(business));
    }

    @Override
    public BusinessDTO delete(Long id) {
        return null;
    }

    private BusinessDTO convertToDTO(Business business) {
        return new BusinessDTO(
                business.getId(),
                business.getName(),
                business.getServiceFee(),
                business.getTax(),
                business.getLogo(),
                business.getMenus()
        );
    }
}
