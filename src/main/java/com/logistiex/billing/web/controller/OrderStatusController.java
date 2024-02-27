package com.logistiex.billing.web.controller;

import com.logistiex.billing.data.model.OrderStatus;
import com.logistiex.billing.data.repository.OrderStatusRepository;
import com.logistiex.billing.service.OrderStatusService;
import com.logistiex.billing.service.dto.OrderStatusDTO;
import com.logistiex.common.web.mvc.controller.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderStatusController extends BaseCrudController<String, OrderStatusDTO, OrderStatus> {

    @Autowired
    private OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService, OrderStatusRepository orderStatusRepository) {
        super(orderStatusService, orderStatusRepository);
    }

    @GetMapping("/orderStatus/{order_id}")
    public ResponseEntity<OrderStatusDTO> getOrderStatus(@PathVariable("order_id") String orderId) {
        OrderStatusDTO orderStatus = orderStatusService.getOrderStatus(orderId, "2024-02-26");
        if (orderStatus != null) {
            return ResponseEntity.ok(orderStatus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
