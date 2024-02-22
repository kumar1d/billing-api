package com.logistiex.billing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.service.BaseCrudService;
import com.logistiex.common.service.mapper.EntityMapper;
import com.logistiex.billing.data.model.Order;
import com.logistiex.billing.service.dto.OrderDTO;
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
public class OrderService extends BaseCrudService<String, OrderDTO, Order> {

    private final String JUSPAY_API_URL = "https://api.juspay.in/orders";
    private final String JUSPAY_API_KEY = "AC109F08CD04F9F8A59725C3B860B0";
    private final String MERCHANT_ID = "dtu";

    @Autowired
    private ObjectMapper objectMapper;

    public OrderService(BaseRepository<Order, String> repository, EntityMapper<OrderDTO, Order> mapper) {
        super(repository, mapper);
    }

    @Override
    protected void afterCreate(OrderDTO dto, Order entity) {
        super.afterCreate(dto, entity);

        createOrder(dto);

    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        HttpHeaders headers = new HttpHeaders();
      headers.set("x-merchantid", MERCHANT_ID);
       headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((JUSPAY_API_KEY + ":").getBytes()));
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<OrderDTO> requestEntity = new HttpEntity<>(orderDTO, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<OrderDTO> responseEntity = restTemplate.exchange(
                    JUSPAY_API_URL,
                    HttpMethod.POST,
                    requestEntity,
                    OrderDTO.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                return responseEntity.getBody();
            } else {
                log.error("Error: {}", responseEntity.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            log.error("Error creating order: {}", e.getMessage());
            return null;
        }
    }
}
