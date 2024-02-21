package com.logistiex.billing.data.model;
import com.logistiex.common.data.model.AuditableBaseEntity;
import com.logistiex.common.generator.annotations.GenerateCrud;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Customer extends AuditableBaseEntity<String> {
    private String object;
    @NotBlank
    private String ObjectReferenceId;
    @NotNull
    private String mobileCountryCode;
    @NotBlank
    private String mobileNumber;
    @NotBlank
    private String emailAddress;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private Instant dateCreated;
    private Instant lastUpdated;
}
