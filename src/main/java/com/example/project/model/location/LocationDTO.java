package com.example.project.model.location;

import com.example.project.model.business.Business;
import com.example.project.model.menu.Menu;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@Setter
@Getter
public class LocationDTO {

    private Long id;

    private Double lat;

    private Double lng;

    private Integer zipcode;

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
