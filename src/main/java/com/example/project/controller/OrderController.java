package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.customer.Customer;
import com.example.project.model.location.Location;
import com.example.project.model.order.OrderDTO;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.customer.ICustomerService;
import com.example.project.service.location.ILocationService;
import com.example.project.service.order.IOrderService;
import com.example.project.service.paymethod.IPayMethodService;
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
    private final ILocationService locationService;
    private final ICustomerService customerService;
    private final IPayMethodService payMethodService;

    @Autowired
    public OrderController(IOrderService orderService, IBusinessService businessService, ILocationService locationService,
                           ICustomerService customerService, IPayMethodService payMethodService) {
        this.orderService = orderService;
        this.businessService = businessService;
        this.locationService = locationService;
        this.customerService = customerService;
        this.payMethodService = payMethodService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
        Function<Long, Location> getLocation = ID -> locationService.findById(ID).convertToLocationEntity();
        Function<Long, Business> getBusiness =
                ID -> businessService.findById(ID).convertToBusinessEntity(getLocation);
        Function<Long, Customer> getCustomer = ID -> customerService.findById(ID).convertToCustomerEntity();
        Function<Long, PayMethod> getPayMethod = ID -> payMethodService.findById(ID).convertToPayMethodEntity(getBusiness);
        return orderService.save(orderDTO.convertToOrderEntity(getBusiness, getCustomer, getPayMethod));
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
