package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.common.service.dto.EntityDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentDetail implements EntityDTO<String> {
    private String id;
    private int version;
    private String email;
    private String name;
    private String phone;
    private String productInfo;
    private String amount;
    private String txnId;
    private String hash;
    private String sUrl;
    private String fUrl;
    private String key;
}
