package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.Customer;
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

    @Column(nullable = false, unique = true)
    private Integer tableNumber;

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

    public Order(long id, double tax, int tableNumber, String comment, Business business, Customer customer, PayMethod payMethod) {
        this.id = id;
        this.tax = tax;
        this.tableNumber = tableNumber;
        this.business = business;
        this.customer = customer;
        this.payMethod = payMethod;
        this.comment = comment;
    }

    public OrderDTO convertToDTO() {
        Long businessId = null;
        Long customerId = null;
        if (business != null)
            businessId = business.getId();
        if (customer != null)
            customerId = customer.getId();
        Set<PurchaseDTO> purchaseDTOS =
                purchases.stream().map(Purchase::convertToDTO).collect(Collectors.toSet());

        OrderDTO orderDTO = new OrderDTO(id, tax, tableNumber, comment, businessId, customerId, payMethod.getId(), created_at);
        orderDTO.setPurchases(purchaseDTOS);
        return orderDTO;
    }
}
