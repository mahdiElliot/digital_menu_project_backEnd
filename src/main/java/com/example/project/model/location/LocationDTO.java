package com.example.project.model.location;

import com.example.project.model.business.Business;
import com.example.project.model.menu.Menu;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class LocationDTO {

    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private Double lat;

    @Setter
    @Getter
    private Double lng;

    @Setter
    @Getter
    private Integer zipcode;

    @Setter
    @Getter
    private Integer zoom;

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
