package com.example.project.model.business;

import com.example.project.model.category.Category;
import com.example.project.model.extra.Extra;
import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.model.menu.Menu;
import com.example.project.model.order.Order;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.model.product.Product;
import com.example.project.model.product.ProductDTO;
import com.example.project.model.zone.Zone;
import com.example.project.model.zone.ZoneDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
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

    private String logo;

    @Column(nullable = false)
    private Boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Menu> menus = new HashSet<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Extra> extras = new HashSet<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<PayMethod> payMethods = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "business_zone",
            joinColumns = {@JoinColumn(name = "business_id")},
            inverseJoinColumns = {@JoinColumn(name = "zone_id")}
    )
    private Set<Zone> zones = new HashSet<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    public Business(long id, String name, double serviceFee, double tax, String logo, boolean enabled,
                    Location location) {
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
        Set<ZoneDTO> zoneDTOs = null;
        Set<ProductDTO> productDTOS = null;
        if (payMethods != null)
            payMethodDTOS = payMethods.stream().map(PayMethod::convertToDTO).collect(Collectors.toSet());
        if (zones != null)
            zoneDTOs = zones.stream().map(Zone::convertToDTO).collect(Collectors.toSet());
        if (products != null)
            productDTOS = products.stream().map(Product::convertToDTO).collect(Collectors.toSet());
        LocationDTO locationDTO = null;
        if (location != null)
            locationDTO = location.convertToDTO();
        BusinessDTO businessDTO = new BusinessDTO(id, name, serviceFee, tax, logo, enabled,
                locationDTO, zoneDTOs, payMethodDTOS, productDTOS);
        if (categories != null) businessDTO.setCategories(categories);
        return businessDTO;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serviceFee=" + serviceFee +
                ", tax=" + tax +
                ", logo='" + logo + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}


