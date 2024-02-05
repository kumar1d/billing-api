package com.logistiex.billing.data.repository;

import com.logistiex.common.data.mongo.repository.BaseMongoRepository;
import com.logistiex.billing.data.model.transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the transaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface transactionRepository extends BaseMongoRepository<transaction, String> {

    List<transaction> findByOrgCodeAndTransactionTimeBetween(String orgCode, long startTime, long endTime);
}
