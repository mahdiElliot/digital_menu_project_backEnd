package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.utils.ErrorUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

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
                    if (businessDTO == null)
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business " + ErrorUtils.NOT_FOUND);
                    return businessDTO.convertToBusinessEntity();
                };
    }
}
