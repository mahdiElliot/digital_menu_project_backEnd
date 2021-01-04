package com.example.project.controller;


import com.example.project.model.business.Business;
import com.example.project.model.order.Order;
import com.example.project.model.order.OrderDTO;
import com.example.project.model.order.OrderDTOReceive;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.model.product.Product;
import com.example.project.model.specproduct.SpecificProduct;
import com.example.project.model.specproduct.SpecificProductDTO;
import com.example.project.repositories.business.BusinessRepository;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.order.IOrderService;
import com.example.project.service.paymethod.IPayMethodService;
import com.example.project.service.product.IProductService;
import com.example.project.utils.ErrorUtils;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@RequestMapping(URLUtils.ORDER)
@RestController
public class OrderController extends BaseController {

    private final IOrderService orderService;
    private final IPayMethodService payMethodService;
    private final IProductService productService;

    @Autowired
    public OrderController(IOrderService orderService, IBusinessService businessService, IPayMethodService payMethodService,
                           IProductService productService) {
        super(businessService);
        this.orderService = orderService;
        this.payMethodService = payMethodService;
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO addOrder(@Valid @RequestBody OrderDTOReceive orderDTO) {
        Function<Long, PayMethod> payMethodMapper =
                ID -> {
                    PayMethodDTO payMethodDTO = payMethodService.findById(ID);
                    return payMethodDTO == null ? null : payMethodDTO.convertToPayMethodEntity(businessMapper());
                };
        Order order = orderDTO.convertToOrderEntity(businessMapper(), payMethodMapper);
        Set<SpecificProduct> specificProducts = order.getSpecificProducts();
        for (SpecificProduct purchase : specificProducts) {
            int quantity = purchase.getQuantity();
            Product product = purchase.getProduct();

            if (quantity >= product.getQuantity())
                throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "quantity more than existing one");
            product.setQuantity(product.getQuantity() - quantity);
            productService.save(product);
        }
        return orderService.save(order);
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping(path = "{id}")
    public OrderDTO getOrder(@PathVariable("id") Long id) {
        OrderDTO orderDTO = orderService.findById(id);
        if (orderDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_FOUND);
        return orderDTO;
    }

    @DeleteMapping(path = "{id}")
    public OrderDTO deleteOrder(@PathVariable("id") Long id) {
        OrderDTO orderDTO = orderService.delete(id);
        if (orderDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_FOUND);
        return orderDTO;
    }
}
