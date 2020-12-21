package com.example.project.service;


import java.util.List;

public interface IService<T, DTO, ID> {

    List<DTO> findAll();

    DTO findByName(String name);

    DTO findById(ID id);

    DTO delete(ID id);

    DTO save(T t);

    DTO convertToDTO(T t);

}
