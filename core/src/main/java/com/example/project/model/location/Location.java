package com.example.project.model.location;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double lat;

    private Double lng;

    private Integer zipcode;

    private Integer zoom;

    @OneToOne(mappedBy = "location")
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
