package com.logistiex.billing.data.enums;

import com.logistiex.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum BillingStatus implements BaseEnum  {

    COMPLETED("Completed"),
    FAILED("Failed");

    private final String description;
}
