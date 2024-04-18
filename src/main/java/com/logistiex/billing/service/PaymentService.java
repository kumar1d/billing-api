package com.logistiex.billing.service;

import com.logistiex.billing.data.vo.PaymentCallback;
import com.logistiex.billing.service.dto.PaymentDetail;

public interface PaymentService {
    PaymentDetail proceedPayment(PaymentDetail paymentDetail);
    String payuCallback(PaymentCallback paymentResponse);
}
