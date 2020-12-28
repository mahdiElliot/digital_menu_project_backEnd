package com.example.project.model.business;

import com.example.project.model.location.Location;
import com.example.project.model.paymethod.PayMethodDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.function.Function;

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

    @NotEmpty
    @NotNull
    private String logo;

    @NotNull
    private Boolean enabled;

    private Set<PayMethodDTO> payMethods;

    private Long location_id;

    public BusinessDTO() {
        super();
    }

    public BusinessDTO(long id, @NotNull String name, double service_fee, double tax, @NotNull String logo, boolean enabled,
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
