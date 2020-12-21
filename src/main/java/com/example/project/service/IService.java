package com.example.project.service;


import java.util.List;

public interface IService<T, DTO, I> {

    List<DTO> findAll();

    DTO findByName(String name);

    DTO findById(I id);

    DTO delete(I id);

    DTO save(T t);

    DTO convertToDTO(T t);

}
