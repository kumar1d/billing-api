package com.logistiex.billing.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.logistiex.common.data.model.AuditableBaseEntity;
import com.logistiex.common.generator.annotations.GenerateCrud;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)

public class Refund extends AuditableBaseEntity<String> {

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
    private Instant dateCreated;
    private Instant lastUpdated;
}
