package com.logistiex.billing.data.model;

import com.logistiex.common.data.enricher.ReferenceKey;
import com.logistiex.common.data.model.AuditableBaseEntity;
import com.logistiex.usp.core.data.model.BusinessOrg;
import com.logistiex.usp.core.data.vo.Money;
import com.logistiex.billing.data.enums.TransactionType;
import com.logistiex.billing.data.vo.ChargeDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TransactionItem extends AuditableBaseEntity<String> {

    @NotBlank
    @ReferenceKey(domainClass = BusinessOrg.class, targetAttribute = "orgCode")
    private String orgCode;

    @NotNull
    private Instant transactionTime;

    @NotNull
    private TransactionType transactionType;

    private String transactionDescription;

    @NotBlank
    private String transactionReference;

    @NotNull
    private Money transactionAmount;

    private String courierAwbNo;

    private String courierCode;

    private String shipmentId;

    private String orderId;

    private ChargeDetails chargeDetails;

    @ReferenceKey(domainClass = BillingPeriod.class)
    private String billingPeriodId;
}
