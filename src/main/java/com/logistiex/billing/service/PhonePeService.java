package com.logistiex.billing.service;

import com.logistiex.billing.locator.RuleUtil;
import com.logistiex.billing.service.dto.Payment;
import com.logistiex.billing.service.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.IOException;

@Service
public class PhonePeService {
    @Autowired
    private RuleUtil util;

    public PaymentResponse quickPay(Payment payment) {
        try {
            return util.processPayment(payment);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
