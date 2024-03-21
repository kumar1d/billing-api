package com.logistiex.billing.data.model;

import com.logistiex.common.data.model.AuditableBaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PhonePeTransaction extends AuditableBaseEntity<String> {
    private String merchantId;
    private String merchantTransactionId;
    private String transactionId;
    private int amount;
    private String merchantUserId;
    private String redirectUrl;
    private String redirectMode;
    private String callbackUrl;
    private String mobileNumber;
    private String paymentInstrumentType;
}
