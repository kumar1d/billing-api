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
import com.logistiex.billing.data.model.TransactionItem;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TransactionItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionItemRepository extends BaseMongoRepository<TransactionItem, String> {
}
