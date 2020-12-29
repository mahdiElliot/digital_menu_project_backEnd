package com.example.project.model.zone;

import com.example.project.model.business.BusinessDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
public class ZoneDTO {
    private long id;

    @NotNull
    private double price;

    @NotNull
    private Boolean enabled;

    private Double radius;

    Set<BusinessDTO> businesses;

    public ZoneDTO() {
        super();
    }

    public ZoneDTO(long id, double price, boolean enabled, double radius, Set<BusinessDTO> businesses) {
        this.id = id;
        this.price = price;
        this.enabled = enabled;
        this.radius = radius;
        this.businesses = businesses;
    }

    public Zone convertToZoneEntity() {
        return new Zone(
                id,
                price,
                enabled,
                radius
        );
    }
}