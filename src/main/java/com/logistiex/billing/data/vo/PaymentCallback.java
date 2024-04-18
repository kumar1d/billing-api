package com.logistiex.billing.data.vo;

import com.logistiex.billing.data.enums.PaymentMode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class PaymentCallback {

    private String txnid;
    private String mihpayid;
    private PaymentMode mode;
    private String status;
    private String hash;
}
