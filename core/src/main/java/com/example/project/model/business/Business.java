package com.example.project.model.business;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.example.project.model.category.Category;
import com.example.project.model.extra.Extra;
import com.example.project.model.location.LocationDTO;
import com.example.project.model.menu.Menu;
import com.example.project.model.order.Order;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.model.product.Product;
import com.example.project.model.zone.Zone;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_generator")
    @SequenceGenerator(name = "business_generator", sequenceName = "business_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Double serviceFee;

    private Double tax;

    @Column(nullable = false, unique = true)
    private String logo;

    @Column(nullable = false)
    private Boolean enabled;

    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    @Column(columnDefinition = "geometry", name = "geometry")
    private Geometry location;

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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "business_products",
            joinColumns = {@JoinColumn(name = "business_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Product> products = new HashSet<>();

    public Business() {
    }

    public Business(long id, String name, double serviceFee, double tax, String logo, boolean enabled,
                    Geometry loc) {
        this.id = id;
        this.name = name;
        this.serviceFee = serviceFee;
        this.tax = tax;
        this.logo = logo;
        this.enabled = enabled;
        this.location = loc;
    }

    public BusinessDTO convertToDTO() {
        Set<PayMethodDTO> payMethodDTOS = null;
        double lng = location.getCoordinate().x;
        double lat = location.getCoordinate().y;
        LocationDTO locationDTO = new LocationDTO(lat, lng, 16);
        if (payMethods != null)
            payMethodDTOS = payMethods.stream().map(PayMethod::convertToDTO).collect(Collectors.toSet());
        return new BusinessDTO(
                id,
                name,
                serviceFee,
                tax,
                logo,
                enabled,
                locationDTO,
                payMethodDTOS);
    }
}


