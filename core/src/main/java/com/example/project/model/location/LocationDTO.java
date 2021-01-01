package com.example.project.model.location;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
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
        Geometry location = null;
        try {
            GeometryFactory fact = new GeometryFactory(new PrecisionModel());
            location = new WKTReader(fact).read("POINT (" + lng + " " + lat + ")");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Location(id, zipcode, zoom, location);
    }
}
