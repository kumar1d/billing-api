package com.logistiex.billing.data.enums;

import com.logistiex.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentMode implements BaseEnum {

    NB("NB"),
    DC("DC"),
    CC("CC");
    private final String description;
}
