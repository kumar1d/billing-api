package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.common.service.dto.EntityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderDTO implements EntityDTO<String> {
    private String id;
    private String orderId;
    private Double amount;
    private String currency;
    @NotBlank
    private String customerId;
    private String customerEmail;
    private String customerPhone;
    private String productId;
    private String returnUrl;
    private String description;

    private String billingAddressFirstName;
    private String billingAddressLastName;
    private String billingAddressLine1;
    private String billingAddressLine2;
    private String billingAddressLine3;
    private String billingAddressCity;
    private String billingAddressState;
    private String billingAddressCountry;
    private String billingAddressPostalCode;
    private String billingAddressPhone;
    private String billingAddressCountryCodeIso;
    private String shippingAddressFirstName;
    private String shippingAddressLastName;
    private String shippingAddressLine1;
    private String shippingAddressLine2;
    private String shippingAddressLine3;
    private String shippingAddressCity;
    private String shippingAddressState;
    private String shippingAddressCountry;
    private String shippingAddressPostalCode;
    private String shippingAddressPhone;
    private String shippingAddressCountryCodeIso;
    private String promoCampId;
    private String custId;
    private String offerKey;
    private String gatewayReferenceId;
    private Boolean getClientAuthToken;
    private Double subventionAmount;
}
