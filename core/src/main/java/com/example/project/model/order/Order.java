package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.model.customer.Customer;
import com.example.project.model.customer.CustomerDTO;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.purchase.Purchase;
import com.example.project.model.purchase.PurchaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "corder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name = "order_generator", sequenceName = "order_seq", allocationSize = 1)
    private Long id;

    private Double tax;

    private String comment;

    @Column(nullable = false)
    private Integer tableNumber;

    private Double serviceTip;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Purchase> purchases = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "paymethod_id", nullable = false)
    private PayMethod payMethod;

    @CreationTimestamp
    private Date created_at;

    public Order(long id, double tax, int tableNumber, double serviceTip, String comment, Business business, Customer customer, PayMethod payMethod) {
        this.id = id;
        this.tax = tax;
        this.tableNumber = tableNumber;
        this.business = business;
        this.customer = customer;
        this.payMethod = payMethod;
        this.comment = comment;
        this.serviceTip = serviceTip;
    }

    public OrderDTO convertToDTO() {
        BusinessDTO businessDTO = null;
        CustomerDTO customerDTO = null;
        if (business != null)
            businessDTO = business.convertToDTO();
        if (customer != null)
            customerDTO = customer.convertToDTO();
        Set<PurchaseDTO> purchaseDTOS =
                purchases.stream().map(Purchase::convertToDTO).collect(Collectors.toSet());

        OrderDTO orderDTO = new OrderDTO(id, tax, tableNumber, serviceTip, comment, businessDTO, customerDTO, payMethod.convertToDTO(), created_at);
        orderDTO.setPurchases(purchaseDTOS);
        return orderDTO;
    }
}
