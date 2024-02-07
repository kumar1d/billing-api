/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
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
import com.logistiex.billing.data.model.transaction;
import com.logistiex.billing.service.dto.transactionDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class transactionController extends BaseCrudController<String, transactionDTO, transaction> {

    public transactionController(BaseCrudService<String, transactionDTO, transaction> service, BaseRepository<transaction, String> repository) {
        super(service, repository);
    }
}
