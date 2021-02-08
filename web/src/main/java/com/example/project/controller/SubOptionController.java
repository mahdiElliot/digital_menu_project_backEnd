package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.suboptions.SubOptionDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.extra.IExtraService;
import com.example.project.service.option.IOptionService;
import com.example.project.service.suboptions.ISubOptionService;
import com.example.project.utils.ErrorUtils;
import com.example.project.utils.FileUploadUtil;
import com.example.project.utils.URLUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@RestController
@CrossOrigin(origins = URLUtils.BASE)
public class SubOptionController extends BaseController {
    private final ISubOptionService subOptionService;
    private final IOptionService optionService;
    private final IExtraService extraService;

    @Autowired
    public SubOptionController(ISubOptionService subOptionService, IOptionService optionService,
                               IExtraService extraService, IBusinessService businessService) {
        super(businessService);
        this.subOptionService = subOptionService;
        this.optionService = optionService;
        this.extraService = extraService;
    }

    @Contract(pure = true)
    private @NotNull Function<Long, Extra> extraMapper(Business business) {
        return
                ID -> {
                    ExtraDTO extraDTO = extraService.findById(ID);
                    if (extraDTO == null)
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "extra " + ErrorUtils.NOT_FOUND);
                    return extraDTO.convertToExtraEntity(business);
                };
    }

    @Contract(pure = true)
    private @NotNull Function<Long, Option> optionMapper(Extra extra) {
        return ID -> {
            OptionDTO optionDTO = optionService.findById(ID);
            if (optionDTO == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "option " + ErrorUtils.NOT_FOUND);
            return optionDTO.convertToOptionEntity(extra);
        };
    }

    @PostMapping(URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA + "/{e_id}" + URLUtils.OPTION + "/{o_id}" + URLUtils.SUBOPTION)
    @ResponseStatus(HttpStatus.CREATED)
    public SubOptionDTO addSubOption(
            @PathVariable(name = "b_id") Long id,
            @PathVariable(name = "e_id") Long id2,
            @PathVariable(name = "o_id") Long id3,
            @Valid SubOptionDTO subOptionDTO,
            @RequestParam(name = "photo", required = false) MultipartFile multipartFile
    ) throws IOException {
        return saveUpdate(id, id2, id3, subOptionDTO, multipartFile);
    }

    @GetMapping(URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA + "/{e_id}" + URLUtils.OPTION + "/{o_id}" + URLUtils.SUBOPTION)
    public List<SubOptionDTO> getAll(@PathVariable(name = "b_id") Long id, @PathVariable(name = "e_id") Long id2, @PathVariable(name = "o_id") Long id3) {
        Business business = businessMapper().apply(id);
        Extra extra = extraMapper(business).apply(id2);
        OptionDTO optionDTO = optionService.findById(id3);
        return new ArrayList<>(optionDTO.getSubOptions());
    }

    @PutMapping(URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA + "/{e_id}" + URLUtils.OPTION + "/{o_id}" + URLUtils.SUBOPTION + "/{s_id}")
    public SubOptionDTO updateSubOption(
            @PathVariable(name = "b_id") Long id,
            @PathVariable(name = "e_id") Long id2,
            @PathVariable(name = "o_id") Long id3,
            @PathVariable(name = "s_id") Long id4,
            @Valid SubOptionDTO subOptionDTO,
            @RequestParam(name = "photo", required = false) MultipartFile multipartFile
    ) throws IOException {
        subOptionDTO.setId(id4);
        return saveUpdate(id, id2, id3, subOptionDTO, multipartFile);
    }

    private SubOptionDTO saveUpdate(Long id, Long id2, Long id3, @Valid SubOptionDTO subOptionDTO, MultipartFile multipartFile) throws IOException {
        Business business = businessMapper().apply(id);
        Extra extra = extraMapper(business).apply(id2);
        Option option = optionMapper(extra).apply(id3);
        if (multipartFile != null) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            String uploadDir = URLUtils.API + URLUtils.SUBOPTION + "/photos/";
            subOptionDTO.setImage(uploadDir + fileName);
            SubOptionDTO subOptionDTO2 = subOptionService.save(subOptionDTO.convertToSubOptionEntity(optionMapper(extra).apply(id3)));
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            return subOptionDTO2;
        }
        return subOptionService.save(subOptionDTO.convertToSubOptionEntity(optionMapper(extra).apply(id3)));
    }
}
