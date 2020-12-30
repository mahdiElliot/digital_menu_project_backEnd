package com.example.project.model.location;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class LocationDTO {

    @NotNull
    private Double lat;

    @NotNull
    private Double lng;

    @NotNull
    private Integer zoom;

    public LocationDTO() {
        super();
    }

    public LocationDTO(double lat, double lng, int zoom) {
        this.lat = lat;
        this.lng = lng;
        this.zoom = zoom;
    }
}
