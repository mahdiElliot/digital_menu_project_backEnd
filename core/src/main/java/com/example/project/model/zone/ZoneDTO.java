package com.example.project.model.zone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ZoneDTO {
    private long id;

    @NotNull
    private double price;

    @NotNull
    private Boolean enabled;

    private Double radius;

    public Zone convertToZoneEntity() {
        return new Zone(id, price, enabled, radius);
    }
}
