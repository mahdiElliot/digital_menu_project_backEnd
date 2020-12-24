package com.example.project.model.business;

import com.example.project.model.category.Category;
import com.example.project.model.extra.Extra;
import com.example.project.model.location.Location;
import com.example.project.model.menu.Menu;
import com.example.project.model.order.Order;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.model.zone.Zone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Column(nullable = false, unique = true)
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Double serviceFee;

    @Setter
    @Getter
    private Double tax;

    @Column(nullable = false, unique = true)
    @Setter
    @Getter
    private String logo;

    @Column(nullable = false)
    @Setter
    @Getter
    private Boolean enabled;

    @OneToMany(mappedBy = "business")
    @Setter
    @Getter
    private Set<Menu> menus;

    @OneToMany(mappedBy = "business")
    @Setter
    @Getter
    private Set<Order> orders;

    @OneToMany(mappedBy = "business")
    @Setter
    @Getter
    private Set<Category> categories;

    @OneToMany(mappedBy = "business")
    @Setter
    @Getter
    private Set<Extra> extras;

    @OneToMany(mappedBy = "business")
    @Setter
    @Getter
    private Set<PayMethod> payMethods;

    @ManyToMany(mappedBy = "businesses")
    @Setter
    @Getter
    private Set<Zone> zones;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @Setter
    @Getter
    private Location location;

    public Business() {
    }

    public Business(long id, String name, double serviceFee, double tax, String logo, boolean enabled, Location location) {
        this.id = id;
        this.name = name;
        this.serviceFee = serviceFee;
        this.tax = tax;
        this.logo = logo;
        this.enabled = enabled;
        this.location = location;
    }

    public BusinessDTO convertToDTO() {
        Set<PayMethodDTO> payMethodDTOS = null;
        if (payMethods != null)
            payMethodDTOS = payMethods.stream().map(PayMethod::convertToDTO).collect(Collectors.toSet());
        long l = 0;
        if (location != null)
            l = location.getId();
        return new BusinessDTO(
                id,
                name,
                serviceFee,
                tax,
                logo,
                enabled,
                payMethodDTOS,
                l
        );
    }
}
