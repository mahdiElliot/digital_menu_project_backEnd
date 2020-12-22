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

    @OneToOne(mappedBy = "location")
    private Business business;

    public Location() {
    }

    public Location(Long id, double lat, double lng, Integer zipcode, Integer zoom, Business business) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.zipcode = zipcode;
        this.zoom = zoom;
        this.business = business;
    }
}
