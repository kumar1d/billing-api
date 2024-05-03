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
import com.logistiex.common.validation.ValidCodeValue;
import com.logistiex.usp.core.data.model.BusinessOrg;
import com.logistiex.usp.core.data.vo.Money;
import com.logistiex.billing.data.enums.TransactionType;
import com.logistiex.billing.data.model.BillingPeriod;
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
public class TransactionDTO implements EntityDTO<String> {
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

    @NotBlank
    @ValidCodeValue(codeGroup = "chargeType")
    private String chargeType; //TODO codeValueGroup -> chargeType

    private String transactionDescription;

    @NotBlank
    private String transactionReference;

    @NotNull
    private Money transactionAmount;

    private Money balance;

    @ValidReference(domainClass = BillingPeriod.class)
    private String billingPeriodId;
}
