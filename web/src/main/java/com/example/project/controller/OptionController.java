package com.example.project.controller;

import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.model.option.OptionDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.extra.IExtraService;
import com.example.project.service.location.ILocationService;
import com.example.project.service.option.IOptionService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@Controller
public class OptionController extends BaseController {
    protected final IOptionService optionService;
    protected final IExtraService extraService;

    @Autowired
    public OptionController(IOptionService optionService, IExtraService extraService, IBusinessService businessService, ILocationService locationService) {
        super(businessService, locationService);
        this.optionService = optionService;
        this.extraService = extraService;
    }

    protected Function<Long, Extra> extraMapper() {
        return
                ID -> {
                    ExtraDTO extraDTO = extraService.findById(ID);
                    return extraDTO == null ? null : extraDTO.convertToExtraEntity(businessMapper());
                };
    }

    @PostMapping(URLUtils.OPTION)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public OptionDTO addOption(@RequestBody OptionDTO optionDTO) {
        return optionService.save(optionDTO.convertToOptionEntity(extraMapper()));
    }
}
