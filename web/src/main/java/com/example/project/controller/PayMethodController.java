package com.example.project.controller;

import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.paymethod.IPayMethodService;
import com.example.project.utils.ErrorUtils;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequestMapping(URLUtils.PAYMETHOD)
@RestController
public class PayMethodController extends BaseController {
    private final IPayMethodService payMethodService;

    @Autowired
    public PayMethodController(IPayMethodService payMethodService, IBusinessService businessService) {
        super(businessService);
        this.payMethodService = payMethodService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PayMethodDTO addPayMethod(@Valid @RequestBody PayMethodDTO payMethodDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorUtils.NULL_EMPTY);

        return payMethodService.save(payMethodDTO.convertToPayMethodEntity(businessMapper()));
    }
}
