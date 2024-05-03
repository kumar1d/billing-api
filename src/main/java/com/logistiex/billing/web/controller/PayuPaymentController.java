package com.logistiex.billing.web.controller;

import com.logistiex.billing.data.enums.PaymentGateway;
import com.logistiex.billing.data.enums.PaymentRequestStatus;
import com.logistiex.billing.payu.client.response.VerifyPaymentResponse;
import com.logistiex.billing.payu.client.service.PaymentGatewayService;
import com.logistiex.billing.service.PaymentRequestService;
import com.logistiex.billing.service.dto.PaymentRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/hooks/payment/payu")
@Slf4j
public class PayuPaymentController extends PaymentController {

    private final PaymentRequestService paymentRequestService;

    @Value("${logistiex.payment.statusUrl}")
    private String statusUrl;


    public PayuPaymentController(PaymentRequestService paymentRequestService) {
        this.paymentRequestService = paymentRequestService;
    }

    @Override
    public void createPaymentSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        final String txnId = httpServletRequest.getParameter("txnid");
        log.debug("transaction request id {}", txnId);

        validatePaymentStatus(txnId, httpServletRequest);
        //TODO get db from statusUrl
        final Optional<PaymentRequestDTO> paymentRequestDTO = paymentRequestService.getPaymentRequestByTransactionId(txnId);
        if (paymentRequestDTO.isPresent() && StringUtils.hasText(paymentRequestDTO.get().getStatusUrl())) {
            httpServletResponse.sendRedirect(paymentRequestDTO.get().getStatusUrl() + "/" + txnId);
        }else {
            httpServletResponse.sendRedirect(statusUrl + "/" + txnId);
        }
    }

    @Override
    public void createPaymentFailed(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        final String txnId = httpServletRequest.getParameter("txnid");
        log.debug("transaction request id {}", txnId);

        updatePaymentRequest(txnId, httpServletRequest, PaymentRequestStatus.FAILED);
        //TODO get db from statusUrl
        final Optional<PaymentRequestDTO> paymentRequestDTO = paymentRequestService.getPaymentRequestByTransactionId(txnId);
        if (paymentRequestDTO.isPresent() && StringUtils.hasText(paymentRequestDTO.get().getStatusUrl())) {
            httpServletResponse.sendRedirect(paymentRequestDTO.get().getStatusUrl() + "/" + txnId);
        }else {
            httpServletResponse.sendRedirect(statusUrl + "/" + txnId);
        }
    }

    @Override
    protected void validatePaymentStatus(String txnId, HttpServletRequest httpServletRequest) {
        final PaymentGatewayService paymentProvider = paymentRequestService.getPaymentGatewayService(PaymentGateway.PAYU);
        //TODO checking update
        final Optional<VerifyPaymentResponse> verifyPaymentResponse = paymentProvider.verifyPaymentRequest(txnId);
        if (verifyPaymentResponse.isPresent()) {
            if (verifyPaymentResponse.get().getStatus() == 1 && verifyPaymentResponse.get().getTransactionDetails().getStatus().equals("success")) {
                updatePaymentRequest(txnId, httpServletRequest, PaymentRequestStatus.SUCCESS);
            } else {
                updatePaymentRequest(txnId, httpServletRequest, PaymentRequestStatus.PENDING_VERIFICATION);
            }
        }
    }

    @Override
    protected void updatePaymentRequest(String txnId, HttpServletRequest request, PaymentRequestStatus status) {
        final PaymentGatewayService paymentProvider = paymentRequestService.getPaymentGatewayService(PaymentGateway.PAYU);
        final Map<String, Object> parsedResponse = paymentProvider.parseResponse(request);

        final Optional<PaymentRequestDTO> paymentRequestDTO = paymentRequestService.getPaymentRequestByTransactionId(txnId);
        paymentRequestDTO.ifPresent(dto -> paymentRequestService.updatePaymentStatus(request.getParameter("mihpayid"),
                request.getParameter("mode"),
                request.getParameter("bankRefNum"),
                status, parsedResponse,
                dto));
    }
}
