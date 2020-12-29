package com.example.project.service.suboptions;

import com.example.project.model.suboptions.SubOption;
import com.example.project.model.suboptions.SubOptionDTO;
import com.example.project.service.IService;

import java.util.List;

public interface ISubOptionService extends IService<SubOption, SubOptionDTO, Long> {
    List<SubOptionDTO> findAllByOptionId(Long id);
}