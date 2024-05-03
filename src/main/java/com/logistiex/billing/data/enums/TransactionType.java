package com.logistiex.billing.data.enums;

import com.logistiex.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType implements BaseEnum {

    PAYMENT("Payment and Recharge"),
    DEBIT("Debit"),
    CREDIT("Credit");

    private final String description;
}
