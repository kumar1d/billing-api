package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.common.service.dto.EntityDTO;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderStatusDTO implements EntityDTO<String> {
    private String id;
    private String customerEmail;
    private String customerPhone;
    private String customerId;
    private Integer statusId;
    private String status;
    private String merchantId;
    private Double amount;
    private String currency;
    private String orderId;
    private Instant dateCreated;
    private String returnUrl;
    private String productId;
}

