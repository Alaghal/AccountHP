package com.homeproducts.homeacc.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FundsDepositEvent {
    private String id;
    private double amount;
    private double balance;
}
