package com.logistiex.billing.data.model;

import com.logistiex.common.data.model.AuditableBaseEntity;
import com.logistiex.common.generator.annotations.GenerateCrud;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Order extends AuditableBaseEntity<String> {
    private String orderId;
    private Double amount;
    private String currency;
    private String customerId;
    private String customerEmail;
    private String customerPhone;
    private String productId;
    private String returnUrl;
    private String description;
    private String billingAddressFirstName;
    private String billingAddressLastName;
    private String billingAddressLine1;
    private String billingAddressLine2;
    private String billingAddressLine3;
    private String billingAddressCity;
    private String billingAddressState;
    private String billingAddressCountry;
    private String billingAddressPostalCode;
    private String billingAddressPhone;
    private String billingAddressCountryCodeIso;
    private String shippingAddressFirstName;
    private String shippingAddressLastName;
    private String shippingAddressLine1;
    private String shippingAddressLine2;
    private String shippingAddressLine3;
    private String shippingAddressCity;
    private String shippingAddressState;
    private String shippingAddressPostalCode;
    private String shippingAddressPhone;
    private String shippingAddressCountryCodeIso;
    private String shippingAddressCountry;
    private String promoCampId;
    private String custId;
    private String offerKey;
    private String gatewayReferenceId;
    private Boolean getClientAuthToken;
    private Double subventionAmount;
    private Instant dateCreated;
    private Instant lastUpdated;
}
