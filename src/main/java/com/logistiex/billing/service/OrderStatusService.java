/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.service.BaseCrudService;
import com.logistiex.common.service.mapper.EntityMapper;
import com.logistiex.billing.data.model.OrderStatus;
import com.logistiex.billing.service.dto.OrderStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Base64;

@Service
@Slf4j
public class OrderStatusService extends BaseCrudService<String, OrderStatusDTO, OrderStatus> {
    private final String JUSPAY_API_URL = "https://api.juspay.in/order/status";
    private final String JUSPAY_API_KEY = "AC109F08CD04F9F8A59725C3B860B0";
    private final String MERCHANT_ID = "dtu";

    @Autowired
    private ObjectMapper objectMapper;

    public OrderStatusService(BaseRepository<OrderStatus, String> repository, EntityMapper<OrderStatusDTO, OrderStatus> mapper) {
        super(repository, mapper);
    }

    @Override
    protected void afterCreate(OrderStatusDTO dto, OrderStatus entity) {
        super.afterCreate(dto, entity);

    }

    public OrderStatusDTO getOrderStatus(String orderId, String version) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-merchantid", MERCHANT_ID);
        headers.set("Content-Type", "application/x-www-form-urlencoded");
       // headers.set("version", version);

        String auth = JUSPAY_API_KEY + ":" + "";
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(JUSPAY_API_URL)
                .queryParam("order_id", orderId);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OrderStatusDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                OrderStatusDTO.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            log.error("Error: {}", responseEntity.getStatusCode());
            return null;
        }
    }
}
