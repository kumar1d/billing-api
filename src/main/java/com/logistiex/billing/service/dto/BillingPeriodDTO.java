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
import com.logistiex.billing.data.enums.BillingStatus;
import com.logistiex.billing.data.model.Money;
import com.logistiex.common.service.dto.EntityDTO;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

/**
 * DTO object for Category
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BillingPeriodDTO implements EntityDTO<String> {
    private String id;
    private int version;

    @NotNull
    @FutureOrPresent(message = "Start date must be present or in the future")
    private Instant startDate;

    @NotNull
    @FutureOrPresent(message = "End date must be present or in the future")
    private Instant endDate;

    @NotBlank
    private String orgCode;

    @NotNull
    private Money openingBalance;

    private Money totalRecharge;
    private Money totalCredit;
    private Money totalDebit;
    private Money closingBalance;
    private BillingStatus status;


}
