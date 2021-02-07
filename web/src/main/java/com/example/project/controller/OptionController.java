package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.model.option.OptionDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.extra.IExtraService;
import com.example.project.service.option.IOptionService;
import com.example.project.utils.ErrorUtils;
import com.example.project.utils.FileUploadUtil;
import com.example.project.utils.URLUtils;
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
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = URLUtils.BASE)
public class OptionController extends BaseController {
    protected final IOptionService optionService;
    protected final IExtraService extraService;

    @Autowired
    public OptionController(IOptionService optionService, IExtraService extraService, IBusinessService businessService) {
        super(businessService);
        this.optionService = optionService;
        this.extraService = extraService;
    }

    protected Function<Long, Extra> extraMapper(Business business) {
        return
                ID -> {
                    ExtraDTO extraDTO = extraService.findById(ID);
                    if (extraDTO == null)
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "extra " + ErrorUtils.NOT_FOUND);
                    return extraDTO.convertToExtraEntity(business);
                };
    }

    @PostMapping(URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA + "/{e_id}" + URLUtils.OPTION)
    @ResponseStatus(HttpStatus.CREATED)
    public OptionDTO addOption(
            @PathVariable(name = "b_id") Long id,
            @PathVariable(name = "e_id") Long id2,
            @Valid OptionDTO optionDTO,
            @RequestParam(name = "photo", required = false) MultipartFile multipartFile
    ) throws IOException {
        return saveUpdate(id, id2, optionDTO, multipartFile);
    }

    @GetMapping(URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA + "/{e_id}" + URLUtils.OPTION)
    public List<OptionDTO> getAll(@PathVariable(name = "b_id") Long id, @PathVariable(name = "e_id") Long id2) {
        Business business = businessMapper().apply(id);
        ExtraDTO extraDTO = extraService.findById(id2);
        return new ArrayList<>(extraDTO.getOptions());
    }

    @GetMapping(URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA + "/{e_id}" + URLUtils.OPTION + "/{o_id}")
    public OptionDTO getOption(@PathVariable(name = "b_id") Long id, @PathVariable(name = "e_id") Long id2, @PathVariable(name = "o_id") Long id3) {
        Business business = businessMapper().apply(id);
        Extra extra = extraMapper(business).apply(id2);
        OptionDTO optionDTO = optionService.findById(id3);
        if (optionDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "option " + ErrorUtils.NOT_FOUND);
        return optionDTO;
    }

    @PutMapping(URLUtils.BUSINESS + "/{b_id}" + URLUtils.EXTRA + "/{e_id}" + URLUtils.OPTION + "/{o_id}")
    public OptionDTO updateOption(
            @PathVariable(name = "b_id") Long id,
            @PathVariable(name = "e_id") Long id2,
            @PathVariable(name = "o_id") Long id3,
            @Valid OptionDTO optionDTO,
            @RequestParam(name = "photo", required = false) MultipartFile multipartFile
    ) throws IOException {
        optionDTO.setId(id3);
        return saveUpdate(id, id2, optionDTO, multipartFile);
    }

    private OptionDTO saveUpdate(Long id, Long id2, @Valid OptionDTO optionDTO, MultipartFile multipartFile) throws IOException {
        Business business = businessMapper().apply(id);
        optionDTO.setExtra_id(id2);
        Extra extra = extraMapper(business).apply(id2);
        if (multipartFile != null) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            String uploadDir = URLUtils.BUSINESS + "/" + id + URLUtils.EXTRA + "/" + id2 + URLUtils.OPTION + "/photos/";
            optionDTO.setImage(uploadDir + fileName);
            OptionDTO optionDTO2 = optionService.save(optionDTO.convertToOptionEntity(extra));
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            return optionDTO2;
        }
        return optionService.save(optionDTO.convertToOptionEntity(extra));
    }
}
