package com.logistiex.billing.data.model;

import com.logistiex.common.batch.data.model.AbstractUploadRequest;
import com.logistiex.common.data.enricher.ReferenceKey;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TransactionUploadRequest extends AbstractUploadRequest {

    @NotBlank
   // @ReferenceKey(domainClass = Courier.class, targetAttribute = "courierCode")
    private String orgCode;
}