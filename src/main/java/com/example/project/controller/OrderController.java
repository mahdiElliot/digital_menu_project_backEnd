package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.order.OrderDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.order.IOrderService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

@RequestMapping(URLUtils.ORDER)
@Controller
public class OrderController {

    private final IOrderService orderService;
    private final IBusinessService businessService;

    @Autowired
    public OrderController(IOrderService orderService, IBusinessService businessService) {
        this.orderService = orderService;
        this.businessService = businessService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
        Function<Long, Business> getBusiness =
                id -> businessService.findById(id).convertToBusinessEntity();
        return orderService.save(orderDTO.convertToOrderEntity(getBusiness));
    }

    @GetMapping
    @ResponseBody
    public List<OrderDTO> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public OrderDTO getOrder(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

    @DeleteMapping(path = "{id}")
    @ResponseBody
    public OrderDTO deleteOrder(@PathVariable("id") Long id) {
        return orderService.delete(id);
    }
}
