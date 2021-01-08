package com.example.project.model.customer;

import com.example.project.model.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    private String phone_number;

    private Set<OrderDTO> orders;

    public Customer convertToCustomerEntity() {
        return new Customer(id, name, email, phone_number);
    }
}
