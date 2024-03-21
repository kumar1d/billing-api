package com.logistiex.billing.data.repository;

import com.logistiex.common.data.mongo.repository.BaseMongoRepository;
import com.logistiex.billing.data.model.PhonePeTransaction;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the PhonePeTransaction entity.
 */

@SuppressWarnings("unused")
@Repository
public interface PhonePeTransactionRepository extends BaseMongoRepository<PhonePeTransaction, String> {
}
