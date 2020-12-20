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
    public DTO findByName(String name) {
        return null;
    }

    @Override
    public BusinessDTO findById(Long id) {
        return null;
    }

    @Override
    public BusinessDTO save(Business business) {
        return null;
    }

    @Override
    public BusinessDTO delete(Long id) {
        return null;
    }


}
