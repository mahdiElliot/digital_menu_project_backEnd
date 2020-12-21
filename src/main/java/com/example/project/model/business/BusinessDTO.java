package com.example.project.model.business;

import com.example.project.model.menu.Menu;
import com.example.project.model.order.COrder;
import com.example.project.model.paymethod.PayMethod;
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
    private Boolean enabled;

    @Setter
    @Getter
    private Set<Menu> menus;

    @Setter
    @Getter
    private Set<COrder> orders;

    @Setter
    @Getter
    private Set<PayMethod> payMethods;

    public BusinessDTO(long id, String name, double service_fee, double tax, String logo, boolean enabled,
                       Set<Menu> menus, Set<COrder> orders, Set<PayMethod> payMethods) {
        this.id = id;
        this.name = name;
        this.service_fee = service_fee;
        this.tax = tax;
        this.logo = logo;
        this.enabled = enabled;
        this.menus = menus;
        this.orders = orders;
        this.payMethods = payMethods;
    }

    public Business convertToBusinessEntity() {
        return new Business(
                id,
                name,
                service_fee,
                tax,
                logo,
                enabled,
                menus,
                orders,
                payMethods
        );
    }
}
