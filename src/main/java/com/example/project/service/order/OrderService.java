package com.example.project.service.order;

import com.example.project.model.order.COrder;
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
        return ((List<COrder>) orderRepository.findAll())
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO findByName(String name) {
        return null;
    }

    @Override
    public OrderDTO findById(Long id) {
        return orderRepository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public OrderDTO delete(Long id) {
        Optional<COrder> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
            return convertToDTO(order.get());
        }
        return null;
    }

    @Override
    public OrderDTO save(COrder order) {
        return convertToDTO(orderRepository.save(order));
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
