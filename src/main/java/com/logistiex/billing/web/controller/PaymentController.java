package com.logistiex.billing.web.controller;

import com.logistiex.common.web.mvc.controller.BaseController;
import com.logistiex.billing.data.enums.PaymentRequestStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

public abstract class PaymentController extends BaseController {

    @RequestMapping("/success")
    public void createPaymentSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
    }

    @RequestMapping("/failed")
    public void createPaymentFailed(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
    }

    protected abstract void validatePaymentStatus(String txnId, HttpServletRequest request);

    protected abstract void updatePaymentRequest(String txnId, HttpServletRequest request, PaymentRequestStatus status);

}
