package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.billing.data.vo.PaymentLinks;
import com.logistiex.billing.data.vo.SdkPayload;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JuspayResponseDTO{

    private String status; // Assuming this is the DTO's identifier, adjust accordingly if it's different
    // @NotBlank
    private String id;
    private String orderId;

    private PaymentLinks paymentLinks;

    private SdkPayload sdkPayload;
}