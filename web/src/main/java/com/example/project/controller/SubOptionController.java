package com.example.project.controller;

import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.suboptions.SubOptionDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.extra.IExtraService;
import com.example.project.service.location.ILocationService;
import com.example.project.service.option.IOptionService;
import com.example.project.service.suboptions.ISubOptionService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@RequestMapping(URLUtils.SUBOPTION)
@RestController
public class SubOptionController extends OptionController {
    private final ISubOptionService subOptionService;

    @Autowired
    public SubOptionController(ISubOptionService subOptionService, IOptionService optionService,
                               IExtraService extraService, IBusinessService businessService, ILocationService locationService) {
        super(optionService, extraService, businessService, locationService);
        this.subOptionService = subOptionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubOptionDTO addSubOption(@RequestBody SubOptionDTO subOptionDTO) {
        Function<Long, Option> optionMapper =
                ID -> {
                    OptionDTO optionDTO = optionService.findById(ID);
                    return optionDTO == null ? null : optionDTO.convertToOptionEntity(extraMapper());
                };
        return subOptionService.save(subOptionDTO.convertToSubOptionEntity(optionMapper));
    }
}
