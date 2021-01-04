package com.example.project.model.business;

import com.example.project.model.category.Category;
import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.model.product.ProductDTO;
import com.example.project.model.zone.ZoneDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Setter
@Getter
public class BusinessDTO {

    private long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private Double service_fee;

    private Double tax;

    private String logo;

    @NotNull
    private Boolean enabled;

    private LocationDTO location;

    private Set<PayMethodDTO> payMethods;

    private Set<ZoneDTO> zones;

    private Set<ProductDTO> products;

    @JsonIgnore
    @Nullable
    private Set<Category> categories;

    public BusinessDTO() {
        super();
    }

    public BusinessDTO(long id, @NotNull String name, double service_fee, double tax, @NotNull String logo, boolean enabled,
                       LocationDTO location, @Nullable Set<ZoneDTO> zones,
                       @Nullable Set<PayMethodDTO> payMethods, @Nullable Set<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.service_fee = service_fee;
        this.tax = tax;
        this.logo = logo;
        this.enabled = enabled;
        this.location = location;
        this.zones = zones;
        this.payMethods = payMethods;
        this.products = products;
    }

    public Business convertToBusinessEntity() {
        Location l = null;
        if (location != null)
            l = location.convertToLocationEntity();
        Business business = new Business(id, name, service_fee, tax, logo, enabled, l);
        if (zones != null && !zones.isEmpty())
            business.setZones(zones.stream().map(ZoneDTO::convertToZoneEntity).collect(Collectors.toSet()));
        if (products != null && !products.isEmpty()) {
            Map<Long, Category> map =
                    categories == null ? null : categories.stream().collect(Collectors.toMap(Category::getId, e -> e));
            Function<Long, Category> categoryMapper = id -> map == null ? null : map.get(id);
            business.setProducts(products.stream()
                    .map(it -> it.convertToProductEntity(categoryMapper)).collect(Collectors.toSet()));

        }
        return business;
    }
}
