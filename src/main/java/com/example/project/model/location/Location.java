package com.example.project.model.location;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(mappedBy = "location")
    @Setter
    @Getter
    private Business business;

    public Location() {
    }

    public Location(long id, double lat, double lng, int zipcode, int zoom) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.zipcode = zipcode;
        this.zoom = zoom;
    }

    public LocationDTO convertToDTO() {
        return new LocationDTO(
                id, lat, lng, zipcode, zoom
        );
    }
}
