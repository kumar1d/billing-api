package com.logistiex.billing.data.model;

import com.logistiex.common.data.enricher.ReferenceKey;
import com.logistiex.common.data.model.AuditableBaseEntity;
import com.logistiex.common.generator.annotations.GenerateCrud;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@GenerateCrud(basePackage = "com.logistiex.billing")
public class transaction extends AuditableBaseEntity<String> {

   

    @NotBlank
    private String orgCode;

    @NotNull
    private long transactionTime;

    @NotBlank
    private String transactionDescription;

    @NotNull
    private long transactionReference;

    @NotNull
    private long transactionAmount;




}

