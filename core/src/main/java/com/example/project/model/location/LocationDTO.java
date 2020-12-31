package com.example.project.model.location;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class LocationDTO {

    private long id;

    @NotNull
    private Double lat;

    @NotNull
    private Double lng;

    @NotNull
    private Integer zipcode;

    @NotNull
    private Integer zoom;

    public LocationDTO() {
        super();
    }

    public LocationDTO(long id, double lat, double lng, int zipcode, int zoom) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.zipcode = zipcode;
        this.zoom = zoom;
    }

    public Location convertToLocationEntity() {
        return new Location(
                id,
                lat,
                lng,
                zipcode,
                zoom
        );
    }
}
