package com.logistiex.billing.web.controller;

import com.logistiex.billing.service.RefundService;
import com.logistiex.billing.service.dto.RefundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RefundController {

    @Autowired
    private RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping("/orders/{order_id}/refunds")
    public ResponseEntity<RefundDTO> refundOrder(@PathVariable("order_id") String orderId,
                                                 @RequestParam("unique_request_id") String uniqueRequestId,
                                                 @RequestParam("amount") String amount) {
        RefundDTO refund = refundService.refundOrder(orderId, uniqueRequestId, amount);
        if (refund != null) {
            return ResponseEntity.ok(refund);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
