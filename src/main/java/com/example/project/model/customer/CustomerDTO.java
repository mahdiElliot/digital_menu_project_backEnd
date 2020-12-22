package com.example.project.model.customer;

import com.example.project.model.category.Category;
import com.example.project.model.order.COrder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class CustomerDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phone_number;

    @Setter
    @Getter
    private Set<COrder> orders;

    public CustomerDTO(Long id, String name, String email, String phone_number, Set<COrder> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.orders = orders;
    }

    public Customer convertToCustomerEntity(){
        return new Customer(
                id,
                name,
                email,
                phone_number,
                orders
        );
    }
}
