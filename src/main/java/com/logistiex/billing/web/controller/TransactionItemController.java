/*
 * Copyright (c) 2024. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.web.controller;

import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.service.BaseCrudService;
import com.logistiex.common.web.mvc.controller.BaseCrudController;
import com.logistiex.billing.data.model.TransactionItem;
import com.logistiex.billing.service.dto.TransactionItemDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction-items")
public class TransactionItemController extends BaseCrudController<String, TransactionItemDTO, TransactionItem> {

    public TransactionItemController(BaseCrudService<String, TransactionItemDTO, TransactionItem> service, BaseRepository<TransactionItem, String> repository) {
        super(service, repository);
    }
}
