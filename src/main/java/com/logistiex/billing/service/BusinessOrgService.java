/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistiex.common.config.LogistiexDelegateMapping;
import com.logistiex.common.web.mvc.remote.BaseRemoteService;
import com.logistiex.usp.core.data.model.BusinessOrg;
import com.logistiex.usp.core.dto.BusinessOrgDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
class BusinessOrgService extends BaseRemoteService<String, BusinessOrgDTO, BusinessOrg> {
    public BusinessOrgService(WebClient webClient, LogistiexDelegateMapping mappings, ObjectMapper objectMapper) {
        super(webClient, mappings, objectMapper);
    }

    @Override
    protected String getEntityResourceName() {
        return "business-orgs";
    }

    @Override
    public String getTargetService() {
        return "usp-core";
    }
}

