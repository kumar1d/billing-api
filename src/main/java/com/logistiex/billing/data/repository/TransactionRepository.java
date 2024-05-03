package com.logistiex.billing.data.repository;

import com.logistiex.common.data.mongo.repository.BaseMongoRepository;
import com.logistiex.billing.data.model.Transaction;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unused")
@Repository
public interface TransactionRepository extends BaseMongoRepository<Transaction, String> {

}
