package com.logistiex.billing.data.enums;

import com.logistiex.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BillingPeriodStatus implements BaseEnum {

    OPEN("Open"),
    CLOSED("Closed"),
    INVOICED("Invoiced");

    private final String description;
}
