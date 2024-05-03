package com.logistiex.billing.data.model;

import com.logistiex.common.data.enricher.ReferenceKey;
import com.logistiex.common.data.model.AuditableBaseEntity;
import com.logistiex.common.validation.ValidCodeValue;
import com.logistiex.usp.core.data.model.BusinessOrg;
import com.logistiex.usp.core.data.vo.Money;
import com.logistiex.billing.data.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Transaction extends AuditableBaseEntity<String> {

    @NotBlank
    @ReferenceKey(domainClass = BusinessOrg.class, targetAttribute = "orgCode")
    private String orgCode;

    @NotNull
    private Instant transactionTime;

    @NotNull
    private TransactionType transactionType;

    @NotBlank
    @ValidCodeValue(codeGroup = "chargeType")
    private String chargeType; //TODO codeValueGroup -> chargeType

    private String transactionDescription;

    @NotBlank
    private String transactionReference;

    @NotNull
    private Money transactionAmount;

    @NotNull
    private Money balance;

    @ReferenceKey(domainClass = BillingPeriod.class)
    private String billingPeriodId;
}

