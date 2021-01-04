package com.example.project.service.order;

import com.example.project.model.customer.Customer;
import com.example.project.model.order.Order;
import com.example.project.model.order.OrderDTO;
import com.example.project.repositories.customer.CustomerRepository;
import com.example.project.repositories.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<OrderDTO> findAll() {
        return ((List<Order>) orderRepository.findAll())
                .stream()
                .map(Order::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO findByName(String name) {
        return null;
    }

    @Override
    public OrderDTO findById(Long id) {
        if (id == null) return null;
        return orderRepository.findById(id)
                .map(Order::convertToDTO).orElse(null);
    }

    @Override
    public OrderDTO delete(Long id) {
        if (id == null) return null;
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
            return order.get().convertToDTO();
        }
        return null;
    }

    @Override
    public OrderDTO save(Order order) {
        Customer customer = order.getCustomer();
        customer = customerRepository.save(customer);
        order.setCustomer(customer);
        return orderRepository.save(order).convertToDTO();
    }
}
