package com.logistiex.billing.data.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@Data
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Payload implements Serializable {

    private String clientId;
    private Double amount;

    private String merchantId;

    private String clientAuthToken;
    private String clientAuthTokenExpiry;
    private String environment;
    private String lastName;
    private String action;
    private String customerId;
    private String returnUrl;
    private String currency;
    private Integer version;
    private String firstName;
    private String id;
    private String customerPhone;
    private String customerEmail;

    private String orderId;
    private String description;
}
