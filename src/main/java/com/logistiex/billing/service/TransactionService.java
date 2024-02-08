/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.service;

import com.logistiex.billing.data.model.Transaction;
import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.service.BaseCrudService;
import com.logistiex.common.service.mapper.EntityMapper;
import com.logistiex.billing.service.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionService extends BaseCrudService<String, TransactionDTO, Transaction> {

    public TransactionService(BaseRepository<Transaction, String> repository, EntityMapper<TransactionDTO, Transaction> mapper) {
        super(repository, mapper);
    }

}
