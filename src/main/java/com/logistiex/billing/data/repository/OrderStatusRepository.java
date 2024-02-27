/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.data.repository;


import com.logistiex.common.data.mongo.repository.BaseMongoRepository;
import com.logistiex.billing.data.model.OrderStatus;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OrderStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderStatusRepository extends BaseMongoRepository<OrderStatus, String> {
}
