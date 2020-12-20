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
    private Double service_fee;

    @Setter
    @Getter
    private Double tax;

    @Setter
    @Getter
    private String logo;

    @Setter
    @Getter
    private Set<Menu> menus;

    public BusinessDTO(long id, String name, double service_fee, double tax, String logo, Set<Menu> menus) {
        this.id = id;
        this.name = name;
        this.service_fee = service_fee;
        this.tax = tax;
        this.logo = logo;
        this.menus = menus;
    }

    public Business convertToBusinessEntity() {
        return new Business(
                id,
                name,
                service_fee,
                tax,
                logo,
                menus
        );
    }
}
