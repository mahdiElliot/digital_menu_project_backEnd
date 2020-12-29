package com.example.project.controller;

import com.example.project.model.extra.ExtraDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.extra.IExtraService;
import com.example.project.service.location.ILocationService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(URLUtils.EXTRA)
@RestController
public class ExtraController extends BaseController {
    private final IExtraService extraService;

    @Autowired
    public ExtraController(IExtraService extraService, IBusinessService businessService, ILocationService locationService) {
        super(businessService, locationService);
        this.extraService = extraService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExtraDTO addExtra(@RequestBody ExtraDTO extraDTO) {
        return extraService.save(extraDTO.convertToExtraEntity(businessMapper()));
    }
}
