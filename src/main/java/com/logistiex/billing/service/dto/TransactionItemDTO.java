/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.billing.data.enums.ChargeType;
import com.logistiex.billing.data.enums.PaymentType;
import com.logistiex.billing.data.enums.ShipmentType;
import com.logistiex.common.service.dto.EntityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;  // Import for Min annotation
import lombok.*;

/**
 * DTO object for Category
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionItemDTO implements EntityDTO<String> {
    private String id;
    private int version;

    @NotNull
    private Long transactionDate;

    @NotBlank
    private String orgCode;

    @NotNull
    private ChargeType chargeType;   // *

    @NotBlank
    private String courierAwbNo;

    @NotBlank
    private String shipmentValue;

    @NotBlank
    private String sourcePincode;

    @NotBlank
    private String sourceCity;

    @NotBlank
    private String destinationPincode;

    @NotBlank
    private String destinationCity;

    @NotBlank
    private String zone;

    @NotBlank
    private String courierCode;

    @NotNull
    @Min(value = 0, message = "Dead weight must be greater than or equal to 0.0")
    private Double deadWeight;

    @NotNull
    @Min(value = 0, message = "Volume weight must be greater than or equal to 0.0")
    private Double volumeWeight;

    @NotNull
    @Min(value = 0, message = "Charge weight must be greater than or equal to 0.0")
    private Double chargeWeight;

    @NotNull
    @Min(value = 1, message = "Weight slab must be greater than or equal to 1")
    private Integer weightSlab;

    @NotNull
    private ShipmentType shipmentType;

    @NotNull
    private PaymentType paymentType;    // *

    @NotNull
    @Min(value = 0, message = "Forward freight must be greater than or equal to 0")
    private Integer forwardfreight;

    private Double codAmount;

    @NotNull
    @Min(value = 0, message = "RTO freight must be greater than or equal to 0")
    private Integer rtoFreight;

    @NotNull
    @Min(value = 0, message = "Total freight must be greater than or equal to 0")
    private Integer totalFreight;
}
