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
import com.logistiex.billing.data.model.BillingPeriod;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the BillingPeriod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillingPeriodRepository extends BaseMongoRepository<BillingPeriod, String> {

    @Query("{'$and': [{'orgCode':?0}, {billingStatus:'OPEN'}, {'deleted': false} ]}")
    Optional<BillingPeriod> findByOpenBillingPeriod(String orgCode);

    @Query("{'$and': [{'orgCode':?0}, {'startDate': {$lte: ?1}}, {'endDate': {$gte: ?1}}, {billingStatus:'OPEN'}, {'deleted': false} ]}")
    Optional<BillingPeriod> findByOpenBillingPeriod(String orgCode, Instant instant);

}
