/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttributeType {

    TEXT("Text"),
    INTEGER("Integer"),
    NUMERIC("Numeric"),
    BOOLEAN("Boolean"),
    DATE("Date"),
    SIZE("Size"),
    IMAGE("Image");

    private final String description;
}
