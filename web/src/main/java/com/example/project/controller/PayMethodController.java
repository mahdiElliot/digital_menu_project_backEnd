package com.example.project.controller;

import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.location.ILocationService;
import com.example.project.service.paymethod.IPayMethodService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping(URLUtils.PAYMETHOD)
@Controller
public class PayMethodController extends BaseController {
    private final IPayMethodService payMethodService;

    @Autowired
    public PayMethodController(IPayMethodService payMethodService, IBusinessService businessService, ILocationService locationService) {
        super(businessService, locationService);
        this.payMethodService = payMethodService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PayMethodDTO addPayMethod(@RequestBody PayMethodDTO payMethodDTO) {
        return payMethodService.save(payMethodDTO.convertToPayMethodEntity(businessMapper()));
    }
}
