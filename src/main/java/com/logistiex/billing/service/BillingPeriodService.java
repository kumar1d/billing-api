package com.logistiex.billing.service;

import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.service.BaseCrudService;
import com.logistiex.common.service.mapper.EntityMapper;
import com.logistiex.billing.data.model.BillingPeriod;
import com.logistiex.billing.data.repository.TransactionRepository;
import com.logistiex.billing.service.dto.BillingPeriodDTO;
import com.logistiex.billing.service.mapper.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingPeriodService extends BaseCrudService<String, BillingPeriodDTO, BillingPeriod> {

    public BillingPeriodService(BaseRepository<BillingPeriod, String> repository, EntityMapper<BillingPeriodDTO, BillingPeriod> mapper, TransactionRepository transactionRepo, TransactionMapper transactionMapper) {
        super(repository, mapper);
    }
}
