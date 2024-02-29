package com.logistiex.billing.data.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@Data
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SdkPayload implements Serializable {
    private String requestId;

    private String service;

    private Payload payload;
}
