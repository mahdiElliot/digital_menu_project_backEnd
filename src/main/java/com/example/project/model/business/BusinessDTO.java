package com.example.project.model.business;

import com.example.project.model.category.Category;
import com.example.project.model.category.CategoryDTO;
import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.model.menu.Menu;
import com.example.project.model.menu.MenuDTO;
import com.example.project.model.order.Order;
import com.example.project.model.order.OrderDTO;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.model.zone.Zone;
import com.example.project.model.zone.ZoneDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

import java.util.Set;
import java.util.function.Function;

@Setter
@Getter
public class BusinessDTO {

    private Long id;

    private String name;

    private Double service_fee;

    private Double tax;

    private String logo;

    private Boolean enabled;

    private Set<PayMethodDTO> payMethods;

    private Long location_id;

    public BusinessDTO(long id, String name, double service_fee, double tax, String logo, boolean enabled,
                       @Nullable Set<PayMethodDTO> payMethods, Long location_id) {
        this.id = id;
        this.name = name;
        this.service_fee = service_fee;
        this.tax = tax;
        this.logo = logo;
        this.enabled = enabled;
        this.payMethods = payMethods;
        this.location_id = location_id;
    }

    public Business convertToBusinessEntity(@NotNull Function<Long, Location> getLocation) {
        return new Business(
                id,
                name,
                service_fee,
                tax,
                logo,
                enabled,
                getLocation.apply(location_id)
        );
    }
}
