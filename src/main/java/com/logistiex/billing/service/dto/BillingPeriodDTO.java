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
import com.logistiex.billing.data.enums.BillingPeriodStatus;
import jakarta.validation.constraints.FutureOrPresent;
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
public class BillingPeriodDTO implements EntityDTO<String> {

    private String id;
    private Integer version;

    @NotNull
    @FutureOrPresent(message = "Start date must be present or in the future")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant startDate;

    @NotNull
    @FutureOrPresent(message = "End date must be present or in the future")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant endDate;

    @NotBlank
    @ValidReference(domainClass = BusinessOrg.class, attribute = "orgCode", remote = true, skipOnError = false)
    private String orgCode;

    @NotNull
    private Money openingBalance;

    private Money totalRecharge;

    private Money totalCredit;

    private Money totalDebit;

    private Money closingBalance;

    private BillingPeriodStatus billingStatus;

}
