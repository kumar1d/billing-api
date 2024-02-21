package com.logistiex.billing.data.model;

import com.logistiex.common.data.model.AuditableBaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Session extends AuditableBaseEntity<String> {
        //@NotBlank
        private String orderId;

        //@NotBlank
        private String amount;

        //@NotBlank
        private String customerId;

        //@NotBlank
        private String customerEmail;

        //@NotBlank
        private String customerPhone;

        // @NotBlank
        private String paymentPageClientId;

        // @NotBlank
        private String action;

        // @NotBlank
        private String returnUrl;

        //@NotBlank
        private String description;

        //@NotBlank
        private String firstName;

        // @NotBlank
        private String lastName;

        private Instant dateCreated;
        private Instant lastUpdated;
}
