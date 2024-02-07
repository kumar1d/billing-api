package com.logistiex.billing.data.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Money {

    private double amount;
    private String currency;

}
