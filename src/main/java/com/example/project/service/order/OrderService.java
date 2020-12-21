package com.example.project.service.order;

import com.example.project.model.order.COrder;
import com.example.project.model.order.OrderDTO;
import com.example.project.repositories.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> findAll() {
        return null;
    }

    @Override
    public OrderDTO findByName(String name) {
        return null;
    }

    @Override
    public OrderDTO findById(Long id) {
        return null;
    }

    @Override
    public OrderDTO delete(Long id) {
        return null;
    }

    @Override
    public OrderDTO save(COrder order) {
        return null;
    }

    @Override
    public OrderDTO convertToDTO(COrder order) {
        return new OrderDTO(
                order.getId(),
                order.getTax(),
                order.getTableNumber(),
                order.getBusiness().getId()
        );
    }
}
