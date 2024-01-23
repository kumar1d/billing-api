/*
 * Copyright (c) 2023. Noetic Logistiex Pvt Ltd - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 *
 * Proprietary and confidential
 */

package com.logistiex.billing;

import com.logistiex.common.CommonComponents;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackageClasses = {CommonComponents.class, BillingApiApplication.class})
@EnableConfigurationProperties({com.logistiex.common.web.config.LogisitiexWebProperties.class, com.logistiex.common.security.config.LogistiexSecurityProperties.class})
public class BillingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingApiApplication.class, args);
    }

}
