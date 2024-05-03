package com.logistiex.billing.payu.client.config;

import com.logistiex.billing.data.enums.PaymentGateway;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "logistiex.payment")
public class PaymentGatewayConfig {
    /**
     * logistiex.payment.service.payu.payuSaltKey
     */
    private Map<String, Map<String, String>> gateways;

    public Map<String, String> getConfigsForPaymentGateway(PaymentGateway paymentGateway) {
        return gateways.getOrDefault(paymentGateway.getDescription(), Collections.emptyMap());
    }
}
