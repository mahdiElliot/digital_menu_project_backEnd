package com.example.project.model.product;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.menu.Menu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Getter
    @Setter
    private Integer price;

    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private String name;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private String images;

    @Setter
    @Getter
    private boolean inventoried;

    @Setter
    @Getter
    private boolean featured;

    @Setter
    @Getter
    private boolean enabled;

    @Setter
    @Getter
    private boolean upselling;

    @Setter
    @Getter
    private Integer offer_price;

    @Setter
    @Getter
    private Integer rank;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    @Setter
    @Getter
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Setter
    @Getter
    private Category category;



}
