package com.logistiex.billing.data.enums;

import com.logistiex.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChargeDetailsType implements BaseEnum {

    SHIPMENT("Shipment");

    private final String description;
}
