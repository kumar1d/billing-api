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
import com.logistiex.billing.data.model.Category;
import com.logistiex.common.service.dto.EntityDTO;
import com.logistiex.common.service.validation.UniqueConstraint;
import com.logistiex.common.service.validation.ValidReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO object for Category
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class transactionDTO implements EntityDTO<String> {
    private String id;
    private int version;

    @NotBlank
  //   @ValidReference(domainClass = BusinessOrg.class,attribute = "orgCode")
    private String orgCode;

    @NotNull
    private long transactionTime;

    @NotBlank
    private String transactionDescription;

    @NotNull
    private long transactionReference;

    @NotNull
    private long transactionAmount;

}
