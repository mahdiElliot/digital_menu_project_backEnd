package com.example.project.service.extra;

import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.service.IService;

import java.util.List;

public interface IExtraService extends IService<Extra, ExtraDTO, Long> {
    List<ExtraDTO> findExtrasByBusinessId(Long id);
}
