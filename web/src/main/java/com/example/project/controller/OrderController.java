package com.example.project.controller;

import com.example.project.model.customer.Customer;
import com.example.project.model.customer.CustomerDTO;
import com.example.project.model.order.OrderDTO;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.customer.ICustomerService;
import com.example.project.service.location.ILocationService;
import com.example.project.service.order.IOrderService;
import com.example.project.service.paymethod.IPayMethodService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;

@RequestMapping(URLUtils.ORDER)
@RestController
public class OrderController extends BaseController {

    private final IOrderService orderService;
    private final ICustomerService customerService;
    private final IPayMethodService payMethodService;

    @Autowired
    public OrderController(IOrderService orderService, IBusinessService businessService, ILocationService locationService,
                           ICustomerService customerService, IPayMethodService payMethodService) {
        super(businessService, locationService);
        this.orderService = orderService;
        this.customerService = customerService;
        this.payMethodService = payMethodService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO addOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Function<Long, Customer> customerMapper =
                ID -> {
                    CustomerDTO customerDTO = customerService.findById(ID);
                    return customerDTO == null ? null : customerDTO.convertToCustomerEntity();
                };
        Function<Long, PayMethod> payMethodMapper =
                ID -> {
                    PayMethodDTO payMethodDTO = payMethodService.findById(ID);
                    return payMethodDTO == null ? null : payMethodDTO.convertToPayMethodEntity(businessMapper());
                };
        return orderService.save(orderDTO.convertToOrderEntity(businessMapper(), customerMapper, payMethodMapper));
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping(path = "{id}")
    public OrderDTO getOrder(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

    @DeleteMapping(path = "{id}")
    public OrderDTO deleteOrder(@PathVariable("id") Long id) {
        return orderService.delete(id);
    }
}
