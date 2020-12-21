package com.example.project.model.zone;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "zone")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private double price;

    @Column(nullable = false)
    @Setter
    @Getter
    private Boolean enabled;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "business_zone",
            joinColumns = {@JoinColumn(name = "zone_id")},
            inverseJoinColumns = {@JoinColumn(name = "business_id")}
    )
    @Setter
    @Getter
    Set<Business> businesses;

    public Zone() {
    }

    public Zone(long id, double price, boolean enabled, Set<Business> businesses) {
        this.id = id;
        this.price = price;
        this.enabled = enabled;
        this.businesses = businesses;
    }

}
