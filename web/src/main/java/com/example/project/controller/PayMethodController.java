package com.example.project.controller;

import com.example.project.model.business.Business;
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

@RestController
@CrossOrigin(origins = URLUtils.BASE)
public class PayMethodController extends BaseController {
    private final IPayMethodService payMethodService;

    @Autowired
    public PayMethodController(IPayMethodService payMethodService, IBusinessService businessService) {
        super(businessService);
        this.payMethodService = payMethodService;
    }

    @PostMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.PAYMETHOD)
    @ResponseStatus(HttpStatus.CREATED)
    public PayMethodDTO addPayMethod(@PathVariable(name = "id") Long id, @Valid @RequestBody PayMethodDTO payMethodDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorUtils.NULL_EMPTY);
        payMethodDTO.setBusiness_id(id);
        payMethodDTO.setId(0);
        Business business = businessMapper().apply(id);
        return payMethodService.save(payMethodDTO.convertToPayMethodEntity(business));
    }

    @PutMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.PAYMETHOD + "/{id2}")
    public PayMethodDTO updatePayMethod(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "id2") Long id2,
            @Valid @RequestBody PayMethodDTO payMethodDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorUtils.NULL_EMPTY);
        payMethodDTO.setBusiness_id(id);
        Business business = businessMapper().apply(id);
        payMethodDTO.setId(id2);
        return payMethodService.save(payMethodDTO.convertToPayMethodEntity(business));
    }
}
