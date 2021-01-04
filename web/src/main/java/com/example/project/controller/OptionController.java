package com.example.project.controller;

import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.model.option.OptionDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.extra.IExtraService;
import com.example.project.service.option.IOptionService;
import com.example.project.utils.FileUploadUtil;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class OptionController extends BaseController {
    protected final IOptionService optionService;
    protected final IExtraService extraService;

    @Autowired
    public OptionController(IOptionService optionService, IExtraService extraService, IBusinessService businessService) {
        super(businessService);
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
    public OptionDTO addOption(@Valid OptionDTO optionDTO, @RequestParam("photo") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String uploadDir = URLUtils.OPTION + "/photos/";
        optionDTO.setImage(uploadDir + fileName);
        OptionDTO optionDTO2 = optionService.save(optionDTO.convertToOptionEntity(extraMapper()));
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return optionDTO2;
    }
}
