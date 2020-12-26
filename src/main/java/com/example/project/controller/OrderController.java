package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.model.customer.Customer;
import com.example.project.model.customer.CustomerDTO;
import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.model.order.OrderDTO;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.customer.ICustomerService;
import com.example.project.service.location.ILocationService;
import com.example.project.service.order.IOrderService;
import com.example.project.service.paymethod.IPayMethodService;
import com.example.project.utils.URLUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Contract(pure = true)
    private @NotNull Function<Long, Location> getLocationFunction() {
        return
                ID -> {
                    LocationDTO locationDTO = locationService.findById(ID);
                    return locationDTO == null ? null : locationDTO.convertToLocationEntity();
                };
    }

    @Contract(pure = true)
    private @NotNull Function<Long, Business> getBusinessFunction() {
        return
                ID -> {
                    BusinessDTO businessDTO = businessService.findById(ID);
                    return businessDTO == null ? null : businessDTO.convertToBusinessEntity(getLocationFunction());
                };
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public OrderDTO addOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Function<Long, Customer> getCustomer =
                ID -> {
                    CustomerDTO customerDTO = customerService.findById(ID);
                    return customerDTO == null ? null : customerDTO.convertToCustomerEntity();
                };
        Function<Long, PayMethod> getPayMethod =
                ID -> {
                    PayMethodDTO payMethodDTO = payMethodService.findById(ID);
                    return payMethodDTO == null ? null : payMethodDTO.convertToPayMethodEntity(getBusinessFunction());
                };
        return orderService.save(orderDTO.convertToOrderEntity(getBusinessFunction(), getCustomer, getPayMethod));
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
