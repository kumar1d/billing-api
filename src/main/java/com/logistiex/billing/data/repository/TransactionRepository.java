package com.logistiex.billing.data.repository;

import com.logistiex.billing.data.model.Transaction;
import com.logistiex.common.data.mongo.repository.BaseMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@SuppressWarnings("unused")
@Repository
public interface TransactionRepository extends BaseMongoRepository<Transaction, String> {

}
