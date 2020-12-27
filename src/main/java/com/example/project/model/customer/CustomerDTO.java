package com.example.project.model.customer;

import com.example.project.model.order.Order;
import com.example.project.model.order.OrderDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class CustomerDTO {

    private Long id;

    private String name;

    private String email;

    private String phone_number;

    private Set<OrderDTO> orders;

    public CustomerDTO(Long id, String name, String email, String phone_number, Set<OrderDTO> orders) {
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
                phone_number
        );
    }
}
