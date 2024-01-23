/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.data.model;

import com.logistiex.common.data.model.AuditableBaseEntity;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import com.logistiex.common.data.enricher.BusinessKey;

/**
 * compositeKey
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends AuditableBaseEntity<String> {

        /**
         * Category Code
         */
        @NotBlank
        @BusinessKey
        private String categoryCode;
        /**
         * Category Name
         */
        @NotBlank
        private String categoryName;
}
