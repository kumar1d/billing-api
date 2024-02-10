package com.logistiex.billing.data.model;

import com.logistiex.billing.data.enums.BillingStatus;
import com.logistiex.billing.data.vo.Money;
import com.logistiex.common.data.model.AuditableBaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BillingPeriod extends AuditableBaseEntity<String> {

    @NotNull
    @FutureOrPresent(message = "Start date must be present or in the future")
    private Instant startDate;

    @NotNull
    @FutureOrPresent(message = "End date must be present or in the future")
    private Instant endDate;

    @NotBlank
    private String orgCode;

    @NotNull
    private Money openingBalance;

    private Money totalRecharge;

    private Money totalCredit;

    private Money totalDebit;

    private Money closingBalance;

    private BillingStatus status;
}
