package com.example.project.model.zone;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class ZoneDTO {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private double price;

    @Setter
    @Getter
    private Boolean enabled;

    @Setter
    @Getter
    Set<Business> businesses;

    public ZoneDTO(long id, double price, boolean enabled, Set<Business> businesses) {
        this.id = id;
        this.price = price;
        this.enabled = enabled;
        this.businesses = businesses;
    }

    public Zone convertToZoneEntity() {
        return new Zone(
                id,
                price,
                enabled,
                businesses
        );
    }
}
