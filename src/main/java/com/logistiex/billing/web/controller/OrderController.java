package com.logistiex.billing.web.controller;

import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.web.mvc.controller.BaseCrudController;
import com.logistiex.billing.data.model.Order;
import com.logistiex.billing.service.dto.OrderDTO;
import com.logistiex.billing.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseCrudController<String, OrderDTO, Order> {

    private final OrderService orderService;

    public OrderController(OrderService service, BaseRepository<Order, String> repository) {
        super(service, repository);
        this.orderService = service;
    }
}
