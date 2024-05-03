package com.logistiex.billing.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.logistiex.usp.core.data.enums.OrgType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserProfileDTO {
    private String orgCode;
    private String orgName;
    private OrgType orgType;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    List<String> spans;
    List<String> tenants;
}
