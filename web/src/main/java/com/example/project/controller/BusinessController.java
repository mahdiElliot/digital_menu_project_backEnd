package com.example.project.controller;

import com.example.project.model.business.BusinessDTO;
import com.example.project.model.location.LocationDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.utils.ErrorUtils;
import com.example.project.utils.FileUploadUtil;
import com.example.project.utils.URLUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequestMapping(URLUtils.BUSINESS)
@RestController
public class BusinessController extends BaseController {

    @Autowired
    public BusinessController(IBusinessService businessService) {
        super(businessService);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessDTO addBusiness(@Valid BusinessDTO businessDTO, @RequestParam("img_logo") MultipartFile multipartFile,
                                   @RequestParam("loc") String location, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "null or empty field");
        if (!location.isBlank()) {
            ObjectMapper objectMapper = new ObjectMapper();
            LocationDTO locationDTO = objectMapper.readValue(location, LocationDTO.class);
            businessDTO.setLocation(locationDTO);
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String uploadDir = URLUtils.BUSINESS + "/photos/";
        businessDTO.setLogo(uploadDir + fileName);
        BusinessDTO businessDTO2 = businessService.save(businessDTO.convertToBusinessEntity());
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return businessDTO2;
    }

    @GetMapping
    public List<BusinessDTO> getAllBusinesses() {
        return businessService.findAll();
    }

    @GetMapping(path = "{id}")
    public BusinessDTO getBusiness(@PathVariable("id") Long id) {
        BusinessDTO businessDTO = businessService.findById(id);
        if (businessDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_FOUND);
        return businessDTO;
    }

    @DeleteMapping(path = "{id}")
    public BusinessDTO deleteBusiness(@PathVariable("id") Long id) {
        BusinessDTO businessDTO = businessService.delete(id);
        if (businessDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_DELETED);
        return businessDTO;
    }

    @PutMapping(path = "{id}")
    public BusinessDTO updateBusiness(@PathVariable("id") Long id, @Valid @RequestBody BusinessDTO businessDTO) {
        businessDTO.setId(id);
        return businessService.save(businessDTO.convertToBusinessEntity());
    }
}
