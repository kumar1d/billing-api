package com.logistiex.billing.data.enums;

import com.logistiex.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShipmentType implements BaseEnum {
    FORWARD("Forward"),
    REVERSE("Reverse");


    private final String description;
}
