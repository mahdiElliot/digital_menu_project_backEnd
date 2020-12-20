package com.example.project.model.business;

import com.example.project.model.menu.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class BusinessDTO {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Double serviceFee;

    @Setter
    @Getter
    private Double tax;

    @Setter
    @Getter
    private String logo;

    @Setter
    @Getter
    private Set<Menu> menus;
}
