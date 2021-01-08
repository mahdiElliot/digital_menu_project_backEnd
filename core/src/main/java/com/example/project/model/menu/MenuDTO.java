package com.example.project.model.menu;

import com.example.project.model.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private long id;

    @NotEmpty
    private String name;

    @NotNull
    private Boolean enabled;

    private Long business_id;

    @Nullable
    private Set<ProductDTO> products;
}
