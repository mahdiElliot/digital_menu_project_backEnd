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
    private double lat;

    @Setter
    @Getter
    private double lng;

    @Setter
    @Getter
    private Integer zipcode;

    @Setter
    @Getter
    private Integer zoom;

    @Setter
    @Getter
    private Long business_id;


    public LocationDTO(Long id, double lat, double lng, Integer zipcode, Integer zoom, Long business_id) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.zipcode = zipcode;
        this.zoom = zoom;
        this.business_id = business_id;
    }

    public Location convertToLocationEntity(@NotNull Function<Long, Business> getBusiness) {
        return new Location(
                id,
                lat,
                lng,
                zipcode,
                zoom,
                getBusiness.apply(business_id)
        );
    }
}
