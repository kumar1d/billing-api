/*
 * Copyright (c) 2024. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.data.repository;


import com.logistiex.common.data.mongo.repository.BaseMongoRepository;
import com.logistiex.billing.data.model.PaymentRequest;
import com.logistiex.billing.service.dto.PaymentRequestDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data MongoDB repository for the PaymentRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRequestRepository extends BaseMongoRepository<PaymentRequest, String> {

    Optional<PaymentRequestDTO> findByTxnIdAndDeletedFalse(String txnId);
}
