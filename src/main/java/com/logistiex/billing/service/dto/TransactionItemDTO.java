/*
 * Copyright (c) 2024. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.common.service.dto.EntityDTO;
import com.logistiex.common.service.validation.ValidReference;
import com.logistiex.usp.core.data.model.BusinessOrg;
import com.logistiex.usp.core.data.vo.Money;
import com.logistiex.billing.data.enums.TransactionType;
import com.logistiex.billing.data.model.BillingPeriod;
import com.logistiex.billing.data.vo.ChargeDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

/**
 * DTO object for TransactionItemDTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionItemDTO implements EntityDTO<String> {
    private String id;
    private Integer version;

    @NotBlank
    @ValidReference(domainClass = BusinessOrg.class, attribute = "orgCode", remote = true, skipOnError = false)
    private String orgCode;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant transactionTime;

    @NotNull
    private TransactionType transactionType;

    private String transactionDescription;

    @NotBlank
    private String transactionReference;

    @NotNull
    private Money transactionAmount;

    private String courierAwbNo;

    private String courierCode;

    private String shipmentId;

    private String orderId;

    private ChargeDetails chargeDetails;

    @ValidReference(domainClass = BillingPeriod.class)
    private String billingPeriodId;
}
