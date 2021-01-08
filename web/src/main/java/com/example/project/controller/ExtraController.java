package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.extra.IExtraService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExtraController extends BaseController {
    private final IExtraService extraService;

    @Autowired
    public ExtraController(IExtraService extraService, IBusinessService businessService) {
        super(businessService);
        this.extraService = extraService;
    }

    @PostMapping(path = URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA)
    @ResponseStatus(HttpStatus.CREATED)
    public ExtraDTO addExtra(@PathVariable("b_id") Long id, @RequestBody ExtraDTO extraDTO) {
        extraDTO.setBusiness_id(id);
        Business business = businessMapper().apply(id);
        return extraService.save(extraDTO.convertToExtraEntity(business));
    }

    @PutMapping(path = URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA + "/{id}")
    public ExtraDTO updateExtra(@PathVariable("b_id") Long bId, @PathVariable("id") Long id, @RequestBody ExtraDTO extraDTO) {
        extraDTO.setBusiness_id(bId);
        Business business = businessMapper().apply(id);
        extraDTO.setId(id);
        return extraService.save(extraDTO.convertToExtraEntity(business));
    }
}
