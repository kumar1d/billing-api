package com.logistiex.billing.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShipmentType {
    FORWARD("Forward"),
    REVERSE("Reverse");


    private final String description;
}
