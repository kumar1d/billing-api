package com.logistiex.billing.data.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

/**
 * The monetary value
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Money implements Serializable {
    /**
     * The three-digit currency code. In ISO 4217 format.
     */
    private String currencyCode;

    /**
     * The currency amount.
     */
    private Double amount;
}