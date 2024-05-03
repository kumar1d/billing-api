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
import com.logistiex.usp.core.data.vo.Money;
import com.logistiex.billing.data.enums.TransactionType;
import com.logistiex.billing.data.model.BillingPeriod;
import com.logistiex.billing.data.model.Transaction;
import com.logistiex.billing.data.repository.BillingPeriodRepository;
import com.logistiex.billing.service.dto.TransactionDTO;
import com.logistiex.billing.service.mapper.BillingPeriodMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TransactionService extends BaseCrudService<String, TransactionDTO, Transaction> {

    @Autowired
    private BillingPeriodRepository billingPeriodRepository;

    @Autowired
    private BillingPeriodService billingPeriodService;

    @Autowired
    private BillingPeriodMapper billingPeriodMapper;

    public TransactionService(BaseRepository<Transaction, String> repository, EntityMapper<TransactionDTO, Transaction> mapper) {
        super(repository, mapper);
    }

    @Override
    protected TransactionDTO beforeMapping(TransactionDTO dto, Method method) {

        if (method == Method.CREATE) {
//            billingPeriodRepository.findByOpenBillingPeriod(dto.getOrgCode())
//                    .ifPresent(period -> dto.setBillingPeriodId(period.getId()));

            final Optional<BillingPeriod> billingPeriod = billingPeriodRepository.findByOpenBillingPeriod(dto.getOrgCode(), dto.getTransactionTime());
            if (billingPeriod.isPresent()) {
                dto.setBillingPeriodId((billingPeriod.get().getId()));
                calculateTotalAmount(dto, billingPeriod.get());
                billingPeriodService.partialUpdate(billingPeriodMapper.toDto(billingPeriod.get()));
            }
        }
        return super.beforeMapping(dto, method);
    }

    private void calculateTotalAmount(TransactionDTO dto, BillingPeriod billingPeriod) {
        if (dto.getTransactionType() == TransactionType.PAYMENT) {
            // update total recharge
            calculatePaymentAmount(dto, billingPeriod);
        }
        if (dto.getTransactionType() == TransactionType.CREDIT) {
            // update total credit
            calculateCreditAmount(dto, billingPeriod);
        }
        if (dto.getTransactionType() == TransactionType.DEBIT) {
            // update total debit
            calculateDebitAmount(dto, billingPeriod);
        }
    }

    private  void calculatePaymentAmount(TransactionDTO dto, BillingPeriod billingPeriod) {
        final Money totalRecharge = billingPeriod.getTotalRecharge();
        if (totalRecharge != null) {
            final Money money = getMoney(totalRecharge, dto.getTransactionAmount());
            billingPeriod.setTotalRecharge(money);
        }
        // update closing balance in billing period
        final Money closingBalance = billingPeriod.getClosingBalance();
        if (closingBalance != null) {
            final Money money = getMoney(closingBalance, dto.getTransactionAmount());
            billingPeriod.setClosingBalance(money);
            // update balance in transaction
            dto.setBalance(billingPeriod.getClosingBalance());
        }
    }

    private  void calculateCreditAmount(TransactionDTO dto, BillingPeriod billingPeriod) {
        final Money totalCredit = billingPeriod.getTotalCredit();
        if (totalCredit != null) {
            final Money money = getMoney(totalCredit, dto.getTransactionAmount());
            billingPeriod.setTotalCredit(money);
        }
        // update closing balance in billing period
        final Money closingBalance = billingPeriod.getClosingBalance();
        if (closingBalance != null) {
            final Money money = getMoney(closingBalance, dto.getTransactionAmount());
            billingPeriod.setClosingBalance(money);
            // update balance in transaction
            dto.setBalance(billingPeriod.getClosingBalance());
        }
    }

    private void calculateDebitAmount(TransactionDTO dto, BillingPeriod billingPeriod) {
        final Money totalDebit = billingPeriod.getTotalDebit();
        if (totalDebit != null) {
            final Double debit = totalDebit.getAmount() == null ? 0.0 : totalDebit.getAmount() - dto.getTransactionAmount().getAmount();
            Money money = new Money();
            money.setCurrencyCode(totalDebit.getCurrencyCode() == null ? "INR" : totalDebit.getCurrencyCode());
            money.setAmount(debit);
            billingPeriod.setTotalDebit(money);
        }
        // update closing balance in billing period
        final Money closingBalance = billingPeriod.getClosingBalance();
        if (closingBalance != null) {
            final Money money = getMoney(closingBalance, dto.getTransactionAmount());
            billingPeriod.setClosingBalance(money);
            // update balance in transaction
            dto.setBalance(billingPeriod.getClosingBalance());
        }
    }

    private Money getMoney(Money closingBalance, Money currentTransactionAmount) {
        Money money = new Money();
        final Double totalClosingAmount = closingBalance.getAmount() == null ? 0.0 : closingBalance.getAmount() + currentTransactionAmount.getAmount();
        money.setCurrencyCode(closingBalance.getCurrencyCode() == null ? "INR" : closingBalance.getCurrencyCode());
        money.setAmount(totalClosingAmount);
        return money;
    }
}
