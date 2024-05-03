package com.logistiex.billing.payu.client.service;

import com.logistiex.common.beans.ProviderService;
import com.logistiex.billing.payu.client.response.VerifyPaymentResponse;
import com.logistiex.billing.service.dto.PaymentRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

public interface PaymentGatewayService extends ProviderService {

    Map<String, Object> prepareRequest(PaymentRequestDTO paymentRequest);

    Map<String, Object> parseResponse(HttpServletRequest httpServletRequest);

    URI getPaymentRequestUrl(PaymentRequestDTO paymentRequest) throws URISyntaxException;

    Optional<VerifyPaymentResponse> verifyPaymentRequest(String txnId);
}