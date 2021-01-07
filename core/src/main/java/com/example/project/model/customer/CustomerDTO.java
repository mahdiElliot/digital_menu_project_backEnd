package com.example.project.model.customer;

import com.example.project.model.order.OrderDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
public class CustomerDTO {

    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    private String phone_number;

    private Set<OrderDTO> orders;

    public CustomerDTO() {
        super();
    }

    public CustomerDTO(Long id, String name, String email, String phone_number, Set<OrderDTO> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.orders = orders;
    }

    public Customer convertToCustomerEntity() {
        Customer customer = new Customer(
                id,
                name,
                email,
                phone_number
        );
        return customer;
    }
}
