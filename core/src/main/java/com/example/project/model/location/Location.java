package com.example.project.model.location;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.example.project.model.business.Business;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
    @SequenceGenerator(name = "location_generator", sequenceName = "location_seq", allocationSize = 1)
    private Long id;

    private Integer zipcode;

    private Integer zoom;

    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    @Column(name = "geometry")
    @Type(type = "com.vividsolutions.jts.geom.Geometry")
    private Geometry location;

    @OneToOne(mappedBy = "location")
    private Business business;

    public Location() {
    }

    public Location(long id, int zipcode, int zoom, Geometry location) {
        this.id = id;
        this.zipcode = zipcode;
        this.zoom = zoom;
        this.location = location;
    }

    public LocationDTO convertToDTO() {
        double lat = location.getCoordinate().y;
        double lng = location.getCoordinate().x;
        return new LocationDTO(
                id, lat, lng, zipcode, zoom
        );
    }
}
