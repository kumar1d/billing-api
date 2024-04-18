package com.logistiex.billing.data.model;

import com.logistiex.billing.data.enums.PaymentMode;
import com.logistiex.billing.data.enums.PaymentStatus;
import com.logistiex.common.data.model.AuditableBaseEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Payment extends AuditableBaseEntity<String> {
    private String email;
    private String name;
    private String phone;
    private String productInfo;
    private Double amount;
    private PaymentStatus paymentStatus;
    private Date paymentDate;
    private String txnId;
    private String mihpayId;
    private PaymentMode mode;
}
