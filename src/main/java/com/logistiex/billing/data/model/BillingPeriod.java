package com.logistiex.billing.data.model;

import com.logistiex.common.data.model.AuditableBaseEntity;
import com.logistiex.common.generator.annotations.GenerateCrud;
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
//@GenerateCrud(basePackage = "com.logistiex.billing")
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
    private Long openingBalance;

}
