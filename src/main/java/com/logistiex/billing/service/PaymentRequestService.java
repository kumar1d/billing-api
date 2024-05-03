/*
 * Copyright (c) 2024. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.service;

import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.service.BaseCrudService;
import com.logistiex.common.service.mapper.EntityMapper;
import com.logistiex.billing.data.enums.PaymentGateway;
import com.logistiex.billing.data.enums.PaymentRequestStatus;
import com.logistiex.billing.data.model.PaymentRequest;
import com.logistiex.billing.data.repository.PaymentRequestRepository;
import com.logistiex.billing.payu.client.config.PaymentGatewayRegistry;
import com.logistiex.billing.payu.client.service.PaymentGatewayService;
import com.logistiex.billing.service.dto.PaymentRequestDTO;
import com.logistiex.billing.service.dto.UserProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class PaymentRequestService extends BaseCrudService<String, PaymentRequestDTO, PaymentRequest> {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Autowired
    private PaymentGatewayRegistry paymentGatewayRegistry;

    @Autowired
    private JanusService janusService;

    public PaymentRequestService(BaseRepository<PaymentRequest, String> repository, EntityMapper<PaymentRequestDTO, PaymentRequest> mapper) {
        super(repository, mapper);
    }

    @Override
    protected PaymentRequestDTO beforeMapping(PaymentRequestDTO dto, Method method) {
        if (method == Method.CREATE) {
            final PaymentGateway paymentGateway = getPaymentGateway();
            dto.setPaymentGateway(paymentGateway);
            dto.setTransactionTime(Instant.now());
            dto.setTxnId(generateTransactionId(paymentGateway.getPrefix()));
            dto.setStatus(PaymentRequestStatus.REQUESTED);
            dto.setProductInfo("Wallet Recharge");
            if (!StringUtils.hasText(dto.getEmail()) && !StringUtils.hasText(dto.getFirstName())) {
                final String userRefId = getUserRefId();
                if (userRefId != null) {
                    final Optional<UserProfileDTO> userDetails = janusService.getUserDetailsByRefId(userRefId);
                    if (userDetails.isPresent()) {
                        dto.setEmail(userDetails.get().getEmail());
                        dto.setFirstName(userDetails.get().getFirstName());
                    }
                }
            }

            final PaymentGatewayService paymentService = getPaymentGatewayService(paymentGateway);
            final Map<String, Object> prepareRequest = paymentService.prepareRequest(dto);
            dto.setRequestData(prepareRequest);
            try {
                final URI paymentRequestUrl = paymentService.getPaymentRequestUrl(dto);
                dto.setPaymentUrl(paymentRequestUrl.toString());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

        }
        return super.beforeMapping(dto, method);
    }

    private PaymentGateway getPaymentGateway() {
        return PaymentGateway.PAYU;
    }

    public PaymentGatewayService getPaymentGatewayService(PaymentGateway paymentGateway) {
        return paymentGatewayRegistry.getProvider(paymentGateway.name());
    }

    private String generateTransactionId(String prefix) {
        StringBuilder transactionId = new StringBuilder(prefix);
        Instant instant = Instant.now();
        LocalDate currentDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(yyyyMMdd);
        transactionId.append(formattedDate);
        transactionId.append(RandomStringUtils.random(6, ALPHABET));

        return new String(transactionId);
    }

    public Optional<PaymentRequestDTO> getPaymentRequestByTransactionId(String txnId) {
        return ((PaymentRequestRepository) repository).findByTxnIdAndDeletedFalse(txnId);
    }

    private String getUserRefId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Transactional
    public void updatePaymentStatus(String gateWayRef, String mode, String bankRefNum, PaymentRequestStatus status, Map<String, Object> parsedResponse, PaymentRequestDTO dto) {
        dto.setGatewayRef(gateWayRef);
        dto.setMode(mode);
        dto.setStatus(status);
        dto.setResponseData(parsedResponse);
        dto.setBankRefNum(bankRefNum);
        log.debug("update to payment request: {}", dto);
        this.update(dto);
    }
}
