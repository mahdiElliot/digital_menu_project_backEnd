package com.example.project.model.zone;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Table(name = "zone")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double price;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "business_zone",
            joinColumns = {@JoinColumn(name = "zone_id")},
            inverseJoinColumns = {@JoinColumn(name = "business_id")}
    )
    Set<Business> businesses;

    public Zone() {
    }

    public Zone(long id, double price, boolean enabled) {
        this.id = id;
        this.price = price;
        this.enabled = enabled;
    }

    public ZoneDTO convertToDTO() {
        Set<BusinessDTO> businessDTOS = null;
        if (businesses != null)
            businessDTOS = businesses.stream().map(Business::convertToDTO).collect(Collectors.toSet());
        return new ZoneDTO(
                id,
                price,
                enabled,
                businessDTOS
        );
    }

}
