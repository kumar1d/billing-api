package com.logistiex.billing.service;

import com.logistiex.billing.service.dto.RefundDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

@Service
@Slf4j
public class RefundService {

    private final String JUSPAY_API_URL = "https://api.juspay.in/orders/{order_id}/refunds";
    private final String JUSPAY_API_KEY = "AC109F08CD04F9F8A59725C3B860B0";
    private final String MERCHANT_ID = "dtu";

    public RefundDTO refundOrder(String orderId, String uniqueRequestId, String amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-merchantid", MERCHANT_ID);
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        String auth = JUSPAY_API_KEY + ":" + "";
        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        headers.set( "Authorization", authHeader );
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("unique_request_id", uniqueRequestId);
        requestBody.add("amount", amount);
        HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RefundDTO> responseEntity = restTemplate.exchange(
                JUSPAY_API_URL.replace("{order_id}", orderId),
                HttpMethod.POST,
                formEntity,
                RefundDTO.class
        );
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return responseEntity.getBody();
        } else {
            log.error("Error: {}", responseEntity.getStatusCode());
            return null;
        }
    }
}
