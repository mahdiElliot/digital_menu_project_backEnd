package com.example.project.model.business;

import com.example.project.model.category.Category;
import com.example.project.model.extra.Extra;
import com.example.project.model.menu.Menu;
import com.example.project.model.order.COrder;
import com.example.project.model.paymethod.PayMethod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Column(nullable = false, unique = true)
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Double serviceFee;

    @Setter
    @Getter
    private Double tax;

    @Column(nullable = false, unique = true)
    @Setter
    @Getter
    private String logo;

    @Column(nullable = false)
    @Setter
    @Getter
    private Boolean enabled;

    @OneToMany(mappedBy = "business")
    @Setter
    @Getter
    private Set<Menu> menus;

    @OneToMany(mappedBy = "business")
    @Setter
    @Getter
    private Set<COrder> orders;

    @OneToMany(mappedBy = "business")
    @Setter
    @Getter
    private Set<Category> categories;

    @OneToMany(mappedBy = "business")
    @Setter
    @Getter
    private Set<Extra> extras;

    @ManyToMany(mappedBy = "businesses")
    @Setter
    @Getter
    private Set<PayMethod> payMethods;

    public Business() {
    }

    public Business(long id, String name, double serviceFee, double tax, String logo,
                    boolean enabled, Set<Menu> menus, Set<COrder> orders, Set<PayMethod> payMethods,
                    Set<Category> categories, Set<Extra> extras) {
        this.id = id;
        this.name = name;
        this.serviceFee = serviceFee;
        this.tax = tax;
        this.logo = logo;
        this.enabled = enabled;
        this.menus = menus;
        this.orders = orders;
        this.payMethods = payMethods;
        this.categories = categories;
        this.extras = extras;
    }
}
