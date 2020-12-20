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
    private Boolean enabled;

    @Setter
    @Getter
    private Set<Menu> menus;

    public BusinessDTO(long id, String name, double serviceFee, double tax, String logo, boolean enabled, Set<Menu> menus) {
        this.id = id;
        this.name = name;
        this.serviceFee = serviceFee;
        this.tax = tax;
        this.logo = logo;
        this.enabled = enabled;
        this.menus = menus;
    }

    public Business convertToBusinessEntity() {
        return new Business(
                id,
                name,
                serviceFee,
                tax,
                logo,
                enabled,
                menus
        );
    }
}
