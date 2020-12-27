package com.example.project.model.zone;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class ZoneDTO {
    private Long id;

    private double price;

    private Boolean enabled;

    Set<BusinessDTO> businesses;

    public ZoneDTO(long id, double price, boolean enabled, Set<BusinessDTO> businesses) {
        this.id = id;
        this.price = price;
        this.enabled = enabled;
        this.businesses = businesses;
    }

    public Zone convertToZoneEntity() {
        return new Zone(
                id,
                price,
                enabled
        );
    }
}
