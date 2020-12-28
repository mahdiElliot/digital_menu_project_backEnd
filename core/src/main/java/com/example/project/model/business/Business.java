package com.example.project.model.business;

import com.example.project.model.category.Category;
import com.example.project.model.extra.Extra;
import com.example.project.model.location.Location;
import com.example.project.model.menu.Menu;
import com.example.project.model.order.Order;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.model.product.Product;
import com.example.project.model.zone.Zone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Table(name = "business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Double serviceFee;

    private Double tax;

    @Column(nullable = false, unique = true)
    private String logo;

    @Column(nullable = false)
    private Boolean enabled;

    @OneToMany(mappedBy = "business")
    private Set<Menu> menus = new HashSet<>();

    @OneToMany(mappedBy = "business")
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "business")
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "business")
    private Set<Extra> extras = new HashSet<>();

    @OneToMany(mappedBy = "business")
    private Set<PayMethod> payMethods = new HashSet<>();

    @ManyToMany(mappedBy = "businesses")
    private Set<Zone> zones = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "business_products",
            joinColumns = {@JoinColumn(name = "business_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Product> products = new HashSet<>();

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
        Long locationId = null;
        if (location != null)
            locationId = location.getId();
        return new BusinessDTO(
                id,
                name,
                serviceFee,
                tax,
                logo,
                enabled,
                payMethodDTOS,
                locationId
        );
    }
}


