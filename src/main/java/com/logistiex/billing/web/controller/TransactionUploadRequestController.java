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
import com.logistiex.billing.data.model.TransactionUploadRequest;
import com.logistiex.billing.service.dto.TransactionUploadRequestDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction-upload-requests")
public class TransactionUploadRequestController extends BaseCrudController<String, TransactionUploadRequestDTO, TransactionUploadRequest> {

    public TransactionUploadRequestController(BaseCrudService<String, TransactionUploadRequestDTO, TransactionUploadRequest> service, BaseRepository<TransactionUploadRequest, String> repository) {
        super(service, repository);
    }
}
