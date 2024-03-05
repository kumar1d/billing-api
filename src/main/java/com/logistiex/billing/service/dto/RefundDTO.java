package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.common.service.dto.EntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RefundDTO implements EntityDTO<String> {
    private String id;

    @JsonProperty("status_id")
    private int statusId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("return_url")
    private String returnUrl;

    @JsonProperty("developer_message")
    private String developerMessage;

    @JsonProperty("user_message")
    private String userMessage;

}
