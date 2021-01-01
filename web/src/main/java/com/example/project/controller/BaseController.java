package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.service.business.IBusinessService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

@Controller
public class BaseController {
    protected final IBusinessService businessService;

    @Autowired
    public BaseController(IBusinessService businessService) {
        this.businessService = businessService;
    }

    @Contract(pure = true)
    protected @NotNull Function<Long, Business> businessMapper() {
        return
                ID -> {
                    BusinessDTO businessDTO = businessService.findById(ID);
                    return businessDTO == null ? null : businessDTO.convertToBusinessEntity();
                };
    }
}
