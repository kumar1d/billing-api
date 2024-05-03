package com.logistiex.billing.payu.client.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistiex.common.exception.BadConfigurationException;
import com.logistiex.billing.data.enums.PaymentGateway;
import com.logistiex.billing.payu.client.config.PaymentGatewayConfig;
import com.logistiex.billing.payu.client.response.VerifyPaymentResponse;
import com.logistiex.billing.service.dto.PaymentRequestDTO;
import com.logistiex.billing.util.HashGenerator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class PayuPaymentService implements PaymentGatewayService {

    private final WebClient webClient;

    private final ObjectMapper objectMapper;

//    private final PaymentGatewayConfig paymentGatewayConfig;

    final Map<String, String> mapping;

    public PayuPaymentService(@Qualifier("payuWebClient") WebClient webClient, ObjectMapper objectMapper, PaymentGatewayConfig paymentGatewayConfig) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;

        final Map<String, String> mapping = paymentGatewayConfig.getConfigsForPaymentGateway(PaymentGateway.PAYU);
        if (mapping != null) {
            this.mapping = mapping;
            final String payuApiUrl1 = mapping.get("payuApiUrl");
            final String key = mapping.get("payuMerchantKey");
            final String saltKey1 = mapping.get("payuSaltKey");
            final String surl1 = mapping.get("surl");
            final String furl1 = mapping.get("furl");
            if (StringUtils.hasText(payuApiUrl1) && StringUtils.hasText(key) && StringUtils.hasText(saltKey1) &&
                    StringUtils.hasText(surl1) &&
                    StringUtils.hasText(furl1)
            ) {
//                this.payuApiUrl = payuApiUrl1;
//                this.merchantKey = key;
//                this.saltKey = saltKey1;
//                this.surl = surl1;
//                this.furl = furl1;
            } else {
                throw new BadConfigurationException("error.missing.service", "Unable to find payment service config", "logistiex.payment.service." + getProviderName().toLowerCase());
            }
        } else {
            throw new BadConfigurationException("error.missing.service", "Unable to find payment service config", "logistiex.payment.service." + getProviderName().toLowerCase());
        }

    }

    @Override
    public Map<String, Object> prepareRequest(PaymentRequestDTO dto) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("key", mapping.get("payuMerchantKey"));
        requestMap.put("txnid", dto.getTxnId());
        requestMap.put("amount", dto.getAmount().getAmount().toString());
        requestMap.put("firstname", dto.getFirstName());
        requestMap.put("email", dto.getEmail());
        requestMap.put("phone", dto.getFirstName());
        requestMap.put("productinfo", dto.getProductInfo());
        requestMap.put("surl", mapping.get("surl"));
        requestMap.put("furl", mapping.get("furl"));
        requestMap.put("hash", generateHash(dto));
        return requestMap;
    }

    @Override
    public Map<String, Object> parseResponse(HttpServletRequest httpServletRequest) {
        final Map<String, Object> payloadData = new HashMap<>();
        for (Map.Entry<String, String[]> entry : httpServletRequest.getParameterMap().entrySet()) {
            String paramName = entry.getKey();
            String[] paramValues = entry.getValue();
            for (String paramValue : paramValues) {
                payloadData.putIfAbsent(paramName, paramValue);
            }
        }
        return payloadData;
    }


    @Override
    public URI getPaymentRequestUrl(PaymentRequestDTO paymentRequest) throws URISyntaxException {
        return new URI(mapping.get("payuApiUrl") + "/_payment");
    }

    @Override
    public Optional<VerifyPaymentResponse> verifyPaymentRequest(String txnId) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("key", mapping.get("payuMerchantKey"));
        formData.add("command", "verify_payment");
        formData.add("var1", txnId);
        formData.add("hash", generateHashForVerifyPayment(txnId));

        try {
            Mono<String> retrievedResource = webClient.post()
                    .uri(verifyPaymentUri())
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class);
            final String clientResponse = retrievedResource.block();
            // Read JSON string
            JsonNode jsonNode = objectMapper.readTree(clientResponse);
            VerifyPaymentResponse verifyPaymentResponse = new VerifyPaymentResponse();
            final JsonNode status = jsonNode.get("status");
            final JsonNode msg = jsonNode.get("msg");
            verifyPaymentResponse.setStatus(status.asInt());
            verifyPaymentResponse.setMsg(status.asText());

            JsonNode transactionDetailsNode = jsonNode.get("transaction_details").get(txnId);
            if (transactionDetailsNode != null) {
                String json = objectMapper.writeValueAsString(transactionDetailsNode);
                final VerifyPaymentResponse.TransactionDetails transactionDetails = objectMapper.readValue(json, VerifyPaymentResponse.TransactionDetails.class);
                verifyPaymentResponse.setTransactionDetails(transactionDetails);
            }
            return Optional.of(verifyPaymentResponse);
        } catch (Exception ex) {
            log.error("Error while calling payu-service. ", ex);
            final VerifyPaymentResponse verifyPaymentResponse = new VerifyPaymentResponse();
            verifyPaymentResponse.setStatus(0);
            verifyPaymentResponse.setMsg(ex.getMessage());
            return Optional.of(verifyPaymentResponse);
        }
    }

    private String verifyPaymentUri() throws URISyntaxException {
        return new URI(mapping.get("payuApiUrl") + "/merchant/postservice?form=2").toString();
    }

    private String generateHash(PaymentRequestDTO paymentRequestDTO) {
        String hashString;
        String hashSequence = "key|txnid|amount|productinfo|firstname|email|||||||||||";
        hashString = hashSequence.concat(mapping.get("payuSaltKey"));
        hashString = hashString.replace("key", mapping.get("payuMerchantKey"));
        hashString = hashString.replace("txnid", paymentRequestDTO.getTxnId());
        hashString = hashString.replace("amount", paymentRequestDTO.getAmount().getAmount().toString());
        hashString = hashString.replace("productinfo", paymentRequestDTO.getProductInfo());
        hashString = hashString.replace("firstname", paymentRequestDTO.getFirstName());
        hashString = hashString.replace("email", paymentRequestDTO.getEmail());

        return HashGenerator.hash("SHA-512", hashString);
    }

    private String generateHashForVerifyPayment(String txnid) {
        String hashString;
        String hashSequence = "key|command|var1|";
        hashString = hashSequence.concat(mapping.get("payuSaltKey"));
        hashString = hashString.replace("key", mapping.get("payuMerchantKey"));
        hashString = hashString.replace("command", "verify_payment");
        hashString = hashString.replace("var1", txnid);

        return HashGenerator.hash("SHA-512", hashString);
    }

    @Override
    public String getProviderName() {
        return "PAYU";
    }

}
