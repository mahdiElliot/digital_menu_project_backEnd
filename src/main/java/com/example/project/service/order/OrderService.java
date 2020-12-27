package com.example.project.service.order;

import com.example.project.model.order.Order;
import com.example.project.model.order.OrderDTO;
import com.example.project.repositories.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> findAll() {
        return ((List<Order>) orderRepository.findAll())
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO findByName(String name) {
        return null;
    }

    @Override
    public OrderDTO findById(Long id) {
        if (id == null) return null;
        return orderRepository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public OrderDTO delete(Long id) {
        if (id == null) return null;
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
            return convertToDTO(order.get());
        }
        return null;
    }

    @Override
    public OrderDTO save(Order order) {
        return convertToDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO convertToDTO(Order order) {
        return order.convertToDTO();
    }
}
