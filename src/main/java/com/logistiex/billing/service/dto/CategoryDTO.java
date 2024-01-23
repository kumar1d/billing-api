/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.service.dto;

import com.logistiex.common.service.dto.EntityDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import com.logistiex.billing.data.model.Category;
import jakarta.validation.constraints.NotBlank;
import com.logistiex.common.service.validation.UniqueConstraint;

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
public class CategoryDTO implements EntityDTO<String> {
    private String id;
    private int version;

    /**
    * Category Code
    */
    @NotBlank
    @UniqueConstraint(domainClass=Category.class, attribute="categoryCode")
    private String categoryCode;
    /**
    * Category Name
    */
    @NotBlank
    private String categoryName;
}
