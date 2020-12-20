package com.example.project.service;


import com.example.project.model.DTO;
import com.example.project.model.menu.Menu;

import java.util.List;

public interface IService {

    List<DTO> findAll();

    DTO findByName(String name);

    DTO findById(Long id);

    DTO delete(Long id);
}
