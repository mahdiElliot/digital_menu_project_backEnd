package com.example.project.model.menu;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Boolean pickup;

    @Setter
    @Getter
    private Boolean delivery;

    @Setter
    @Getter
    private Boolean enabled;

    public MenuEntity(){ }

    public MenuEntity(String name, boolean pickup, boolean delivery, boolean enabled){
        this.name = name;
        this.pickup = pickup;
        this.delivery = delivery;
        this.enabled = enabled;
    }
}
