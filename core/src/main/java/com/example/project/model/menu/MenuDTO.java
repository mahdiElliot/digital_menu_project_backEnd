package com.example.project.model.menu;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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

    public Menu convertToMenuEntity(Business business) {
        Menu menu = new Menu(id, name, enabled, business);
        if (products != null && !products.isEmpty()) {
            Set<Category> categories = business.getCategories();
            Map<Long, Category> map =
                    categories == null ? null : categories.stream().collect(Collectors.toMap(Category::getId, e -> e));
            Function<Long, Category> categoryMapper = id -> map == null ? null : map.get(id);
            menu.setProducts(products.stream()
                    .map(it -> it.convertToProductEntity(categoryMapper.apply(it.getCategory_id()))).collect(Collectors.toSet()));
        }
        return menu;
    }

}
