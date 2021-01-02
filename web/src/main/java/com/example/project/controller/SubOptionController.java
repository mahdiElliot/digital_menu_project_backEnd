package com.example.project.controller;

import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.suboptions.SubOption;
import com.example.project.model.suboptions.SubOptionDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.extra.IExtraService;
import com.example.project.service.option.IOptionService;
import com.example.project.service.suboptions.ISubOptionService;
import com.example.project.utils.FileUploadUtil;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

@RequestMapping(URLUtils.SUBOPTION)
@RestController
public class SubOptionController extends OptionController {
    private final ISubOptionService subOptionService;

    @Autowired
    public SubOptionController(ISubOptionService subOptionService, IOptionService optionService,
                               IExtraService extraService, IBusinessService businessService) {
        super(optionService, extraService, businessService);
        this.subOptionService = subOptionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubOptionDTO addSubOption(SubOptionDTO subOptionDTO, @RequestParam("photo") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Function<Long, Option> optionMapper =
                ID -> {
                    OptionDTO optionDTO = optionService.findById(ID);
                    return optionDTO == null ? null : optionDTO.convertToOptionEntity(extraMapper());
                };
        SubOption subOption = subOptionDTO.convertToSubOptionEntity(optionMapper);
        String uploadDir = URLUtils.SUBOPTION + "/photos/";
        subOption.setImage(uploadDir + fileName);
        SubOptionDTO subOptionDTO2 = subOptionService.save(subOption);
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return subOptionDTO2;
    }
}
