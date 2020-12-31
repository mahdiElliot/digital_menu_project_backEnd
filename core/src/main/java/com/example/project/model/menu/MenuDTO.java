package com.example.project.model.menu;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Setter
@Getter
public class MenuDTO {
    private long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private Boolean enabled;

    private Long business_id;

    private Set<ProductDTO> products;

    public MenuDTO() {
        super();
    }

    public MenuDTO(long id, String name, boolean enabled, Long business_id, @Nullable Set<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.business_id = business_id;
        this.products = products;
    }

    public Menu convertToMenuEntity(@NotNull Function<Long, Business> getBusiness) {
        Business business = getBusiness.apply(business_id);
        Menu menu = new Menu(id, name, enabled, business);
        if (products != null) {
            Set<Category> categories = business.getCategories();
            Function<Long, Category> categoryMapper =
                    id -> categories == null ? null :
                            categories.stream().
                                    filter(it -> it.getId().equals(id)).findFirst().orElse(null);
            menu.setProducts(products.stream()
                    .map(it -> it.convertToProductEntity(categoryMapper)).collect(Collectors.toSet()));
        }
        return menu;
    }

}
