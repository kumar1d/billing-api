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
import com.logistiex.common.web.mvc.controller.BaseCrudController;
import com.logistiex.billing.data.model.BillingPeriod;
import com.logistiex.billing.service.BillingPeriodService;
import com.logistiex.billing.service.dto.BillingPeriodDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/billing-periods")
public class BillingPeriodController extends BaseCrudController<String, BillingPeriodDTO, BillingPeriod> {

    public BillingPeriodController(BillingPeriodService service, BaseRepository<BillingPeriod, String> repository) {
        super(service, repository);
    }
}
