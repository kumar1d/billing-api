package com.logistiex.billing.data.enums;

import com.logistiex.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentType implements BaseEnum {

    PREPAID("Prepaid"),
    POSTPAID("Postpaid"),
    COD("COD");
    private final String description;
}
