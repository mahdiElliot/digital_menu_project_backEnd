package com.example.project.model.zone;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Table(name = "zone")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zone_generator")
    @SequenceGenerator(name = "zone_generator", sequenceName = "zone_seq", allocationSize = 1)
    private Long id;

    private double price;

    @Column(nullable = false)
    private Boolean enabled;

    private Double radius;

    @ManyToMany(mappedBy = "zones")
    Set<Business> businesses = new HashSet<>();

    public Zone() {
    }

    public Zone(long id, double price, boolean enabled, double radius) {
        this.id = id;
        this.price = price;
        this.enabled = enabled;
        this.radius = radius;
    }

    public ZoneDTO convertToDTO() {
        return new ZoneDTO(
                id,
                price,
                enabled,
                radius
        );
    }

}
