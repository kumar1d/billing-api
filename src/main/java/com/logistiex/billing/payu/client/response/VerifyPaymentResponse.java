package com.logistiex.billing.payu.client.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VerifyPaymentResponse implements Serializable {
    private long status;
    private String msg;
    private TransactionDetails transactionDetails;

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class TransactionDetails implements Serializable{
        private String mihpayid;
        private String requestID;
        private String bankRefNum;
        private String amt;
        private String transactionAmount;
        private String txnid;
        private String additionalCharges;
        private String productinfo;
        private String firstname;
        private String bankcode;
        private Object udf1;
        private Object udf3;
        private Object udf4;
        private Object udf5;
        private String field2;
        private String field9;
        private String errorCode;
        private String addedon;
        private String paymentSource;
        private String cardType;
        private String errorMessage;
        private String meCode;
        private String netAmountDebit;
        private String disc;
        private String mode;
        private String pgType;
        private String cardNo;
        private String nameOnCard;
        private Object udf2;
        private String status;
        private String unmappedstatus;
        private String merchantUTR;
        private String settledAt;
    }
}
