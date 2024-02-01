package com.logistiex.billing.data.enums;

import com.logistiex.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChargeType implements BaseEnum {

    DEBIT_CARD("Debit Card"),
    CREDIT_CARD("Credit Card"),
    UPI("UPI"),
    NET_BANKING("Net Banking");

    private final String description;
}