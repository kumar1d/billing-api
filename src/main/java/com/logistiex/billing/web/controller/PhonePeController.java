package com.logistiex.billing.web.controller;

import com.logistiex.billing.service.PhonePeService;
import com.logistiex.billing.service.dto.Payment;
import com.logistiex.billing.service.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/PhonePe")
public class PhonePeController {
    @Autowired
    private PhonePeService service;

    @PostMapping("/payment")
    public PaymentResponse pay(@RequestBody Payment payment){
        return service.quickPay(payment);
    }


}
