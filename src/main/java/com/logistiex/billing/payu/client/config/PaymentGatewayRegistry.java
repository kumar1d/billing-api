package com.logistiex.billing.payu.client.config;

import com.logistiex.common.beans.factory.ProviderBeanFactory;
import com.logistiex.billing.payu.client.service.PaymentGatewayService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayRegistry extends ProviderBeanFactory<PaymentGatewayService> {
    public PaymentGatewayRegistry(ApplicationContext context) {
        super(context);
    }
}
