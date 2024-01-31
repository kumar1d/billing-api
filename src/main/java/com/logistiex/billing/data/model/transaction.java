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
   // @ReferenceKey(domainClass = BusinessOrg.class,targetAttribute = "orgCode")
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

// 1 model create
// 2 dto create
// 3 generate crud annotation on model
// 4 compile
// 5 go to the target folder and check the generate code
// 6 copy from generated folder and paste into the corresponding folder on base package

// 2 class important
//a) BaseCrudController b) BaseCrudService