package com.example.project.service.business;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.service.IService;

import java.util.List;

public interface IBusinessService extends IService {
    BusinessDTO save(Business business);
}
