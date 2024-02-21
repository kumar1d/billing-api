/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */
package com.logistiex.billing.service.mapper;

import com.logistiex.common.service.mapper.EntityMapper;
import com.logistiex.billing.data.model.Session;
import com.logistiex.billing.service.dto.SessionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper extends EntityMapper<SessionDTO, Session> {

}
