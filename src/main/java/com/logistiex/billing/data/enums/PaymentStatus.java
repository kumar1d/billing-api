package com.logistiex.billing.data.enums;

import com.logistiex.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatus implements BaseEnum {

    Pending("Pending"),
    Failed("Failed"),
    Success("Success");
    private final String description;
}
