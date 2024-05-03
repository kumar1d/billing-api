package com.logistiex.billing.data.enums;

import com.logistiex.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentGateway implements BaseEnum {

    PAYU("payu", "PU"),
    CASHFREE("cashfree", "CF");

    private final String description;
    private String prefix;
}
