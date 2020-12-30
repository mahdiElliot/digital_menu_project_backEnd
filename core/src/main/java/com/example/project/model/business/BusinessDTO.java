package com.example.project.model.business;

import com.example.project.model.location.LocationDTO;
import com.example.project.model.paymethod.PayMethodDTO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
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

    private LocationDTO location;

    private Set<PayMethodDTO> payMethods;

    public BusinessDTO() {
        super();
    }

    public BusinessDTO(long id, @NotNull String name, double service_fee, double tax, @NotNull String logo, boolean enabled,
                       LocationDTO location, @Nullable Set<PayMethodDTO> payMethods) {
        this.id = id;
        this.name = name;
        this.service_fee = service_fee;
        this.tax = tax;
        this.logo = logo;
        this.enabled = enabled;
        this.location = location;
        this.payMethods = payMethods;
    }

    public Business convertToBusinessEntity() {
        Geometry geometry = null;
        try {
            GeometryFactory fact = new GeometryFactory(new PrecisionModel());
            geometry = new WKTReader(fact).read("POINT(1 1)");
            if (location != null) {
                double lat = location.getLat();
                double lng = location.getLng();
                geometry = new WKTReader(fact).read("POINT (" + lng + " " + lat + ")");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Business(
                id,
                name,
                service_fee,
                tax,
                logo,
                enabled,
                geometry
        );
    }
}
