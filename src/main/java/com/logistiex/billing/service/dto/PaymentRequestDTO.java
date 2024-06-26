package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.common.service.dto.EntityDTO;
import com.logistiex.common.service.validation.ValidReference;
import com.logistiex.usp.core.data.model.BusinessOrg;
import com.logistiex.usp.core.data.vo.Money;
import com.logistiex.billing.data.enums.PaymentGateway;
import com.logistiex.billing.data.enums.PaymentRequestStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentRequestDTO implements EntityDTO<String> {

    private String id;
    private Integer version;

    @NotBlank
    @ValidReference(domainClass = BusinessOrg.class, attribute = "orgCode", remote = true, skipOnError = false)
    private String orgCode;
    /**
     * This is the unique transaction ID generated by the merchant
     */
    private String txnId;
    /**
     * This is the amount of the transaction
     */
    @NotNull
    private Money amount;
    /**
     * This is the first name of the customer.
     */
    private String firstName;

    private String lastName;
    /**
     * This is the email address of the customer.
     */
    private String email;
    /**
     * This is the phone number of the customer.
     */
    private String phone;
    /**
     * This is the product information.
     */
    private String productInfo;
    /**
     * This is the URL where the customer will be redirected after a successful transaction.
     */
    private String statusUrl;
    /**
     * This is the hash value generated by the merchant with above data
     */
    private String hash;

    private String paymentUrl;

    /**
     * This is the unique transaction ID generated by the payu
     */
    private String gatewayRef;

    /**
     * payment Type eg, CC, NB,
     */
    private String mode;
    /**
     * status Type ->  success, failed
     */
    private PaymentRequestStatus status;

    private Map<String, Object> responseData;

    private Map<String, Object> requestData;


    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant transactionTime;

    private PaymentGateway paymentGateway;
    private String bankRefNum;
}