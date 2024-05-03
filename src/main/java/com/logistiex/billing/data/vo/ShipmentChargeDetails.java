package com.logistiex.billing.data.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.usp.core.data.enums.PaymentMode;
import com.logistiex.usp.core.data.vo.Money;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ShipmentChargeDetails extends ChargeDetails {

    @NotBlank
    private Money shipmentValue;

    @NotBlank
    private String sourcePincode;

    @NotBlank
    private String sourceCity;

    @NotBlank
    private String destinationPincode;

    @NotBlank
    private String destinationCity;

    @NotBlank
    private String zone;

    @NotBlank
    private String courierCode;

    @NotNull
    @Min(value = 0, message = "Dead weight must be greater than or equal to 0.0")
    private Double deadWeight;

    @NotNull
    @Min(value = 0, message = "Volume weight must be greater than or equal to 0.0")
    private Double volumeWeight;

    @NotNull
    @Min(value = 0, message = "Charge weight must be greater than or equal to 0.0")
    private Double chargeWeight;

    @NotNull
    @Min(value = 1, message = "Weight slab must be greater than or equal to 1")
    private Integer weightSlab;

    @NotBlank
    private String serviceType;

    @NotNull
    private PaymentMode paymentMode;

    @NotNull
    @Min(value = 0, message = "Forward freight must be greater than or equal to 0")
    private Integer forwardfreight;

    private Double codAmount;

    @NotNull
    @Min(value = 0, message = "RTO freight must be greater than or equal to 0")
    private Integer rtoFreight;

    @NotNull
    @Min(value = 0, message = "Total freight must be greater than or equal to 0")
    private Integer totalFreight;
}
