package com.example.project.model.menu;

import com.example.project.model.business.Business;
import com.example.project.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
public class MenuDTOReceive {
    private long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private Boolean enabled;

    private Long business_id;

    private Set<Long> products;

    public MenuDTOReceive() {
        super();
    }

    public Menu convertToMenuEntity(Business business) {
        Menu menu = new Menu(id, name, enabled, business);
        Map<Long, Product> map =
                business.getProducts() == null ? null :
                        business.getProducts().stream().collect(Collectors.toMap(Product::getId, e -> e));
        if (map != null) {
            Set<Product> productSet = new HashSet<>();
            for (Long id : products) {
                Product product = map.get(id);
                if (product != null)
                    productSet.add(product);
            }
            menu.setProducts(productSet);
        }
        return menu;
    }
}
