/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing.service;

import com.logistiex.billing.service.dto.CategoryDTO;
import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.service.BaseCrudService;
import com.logistiex.common.service.mapper.EntityMapper;
import com.logistiex.billing.data.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService extends BaseCrudService<String, CategoryDTO, Category> {

    public CategoryService(BaseRepository<Category, String> repository, EntityMapper<CategoryDTO, Category> mapper) {
        super(repository, mapper);
    }
}
