package com.logistiex.billing.data.model;

import com.logistiex.common.data.model.AuditableBaseEntity;
import com.logistiex.common.generator.annotations.GenerateCrud;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OrderStatus extends AuditableBaseEntity<String> {
    private String customerEmail;
    private String customerPhone;
    private String customerId;
    private Integer statusId;
    private String status;
    private String id;
    private String merchantId;
    private Double amount;
    private String currency;
    private String orderId;
    private Instant dateCreated;
    private String returnUrl;
    private String productId;
}
