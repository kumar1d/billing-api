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
import com.logistiex.billing.data.model.BillingPeriod;
import com.logistiex.billing.data.model.TransactionItem;
import com.logistiex.billing.data.repository.BillingPeriodRepository;
import com.logistiex.billing.service.dto.TransactionItemDTO;
import com.logistiex.billing.service.mapper.BillingPeriodMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TransactionItemService extends BaseCrudService<String, TransactionItemDTO, TransactionItem> {

    @Autowired
    private BillingPeriodRepository billingPeriodRepository;

    @Autowired
    private BillingPeriodService billingPeriodService;

    @Autowired
    private BillingPeriodMapper billingPeriodMapper;

    public TransactionItemService(BaseRepository<TransactionItem, String> repository, EntityMapper<TransactionItemDTO, TransactionItem> mapper) {
        super(repository, mapper);
    }

    @Override
    protected TransactionItemDTO beforeMapping(TransactionItemDTO dto, Method method) {
        if (method == Method.CREATE) {

//            billingPeriodRepository.findByOpenBillingPeriod(dto.getOrgCode())
//                    .ifPresent(period -> dto.setBillingPeriodId(period.getId()));

            final Optional<BillingPeriod> billingPeriod = billingPeriodRepository.findByOpenBillingPeriod(dto.getOrgCode(), dto.getTransactionTime());
            billingPeriod.ifPresent(period -> dto.setBillingPeriodId((period.getId())));
        }
        return super.beforeMapping(dto, method);
    }
}
