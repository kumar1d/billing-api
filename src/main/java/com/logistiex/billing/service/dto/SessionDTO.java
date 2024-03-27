package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.common.service.dto.EntityDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SessionDTO implements EntityDTO<String> {
    private String id; // Assuming this is the DTO's identifier, adjust accordingly if it's different

    //  @NotBlank
    private String orderId;

    //   @NotBlank
    private String amount;

    //  @NotBlank
    private String customerId;

    //  @NotBlank
    private String customerEmail;

    //   @NotBlank
    private String customerPhone;

    //  @NotBlank
    private String paymentPageClientId;

    // @NotBlank
    private String action;

    //  @NotBlank
    private String returnUrl;

    // @NotBlank
    private String description;

    // @NotBlank
    private String firstName;

    // @NotBlank
    private String lastName;

    private String Status;


}