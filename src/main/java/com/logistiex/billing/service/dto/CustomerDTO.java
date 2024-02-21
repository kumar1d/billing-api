package com.logistiex.billing.service.dto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.common.service.dto.EntityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerDTO implements EntityDTO<String> {
    private String id;
    private String object;
    private String objectReferenceId;
    @NotBlank
    private String mobileCountryCode;
    @NotBlank
    private String mobileNumber;
    private String emailAddress;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
