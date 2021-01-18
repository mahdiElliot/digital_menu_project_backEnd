package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.extra.IExtraService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = URLUtils.BASE)
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

    @GetMapping(path = URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA)
    public List<ExtraDTO> findExtras(@PathVariable("b_id") Long id) {
        return extraService.findExtrasByBusinessId(id);
    }

    @PutMapping(path = URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA + "/{id}")
    public ExtraDTO updateExtra(@PathVariable("b_id") Long bId, @PathVariable("id") Long id, @RequestBody ExtraDTO extraDTO) {
        extraDTO.setBusiness_id(bId);
        Business business = businessMapper().apply(bId);
        extraDTO.setId(id);
        return extraService.save(extraDTO.convertToExtraEntity(business));
    }

    @DeleteMapping(path = URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA + "/{id}")
    public ExtraDTO deleteExtra(@PathVariable("b_id") Long bId, @PathVariable("id") Long id) {
        Business business = businessMapper().apply(bId);
        return extraService.delete(id);
    }
}
