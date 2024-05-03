package com.logistiex.billing.data.vo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.billing.data.enums.ChargeDetailsType;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "charge_type")
@JsonSubTypes({@JsonSubTypes.Type(value = ShipmentChargeDetails.class, name = "SHIPMENT")})
public abstract class ChargeDetails implements Serializable {

    private ChargeDetailsType chargeType;
}
