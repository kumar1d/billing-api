package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.common.batch.dto.AbstractUploadRequestDTO;
import com.logistiex.common.service.validation.ValidReference;
import com.logistiex.common.vo.FileMeta;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionUploadRequestDTO extends AbstractUploadRequestDTO {

    @NotBlank
    //@ValidReference(domainClass = Courier.class, attribute = "courierCode")
    private String orgCode;

    private FileMeta fileUrlFile;
}