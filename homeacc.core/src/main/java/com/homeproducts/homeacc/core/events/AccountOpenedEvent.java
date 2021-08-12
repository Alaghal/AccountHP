package com.homeproducts.homeacc.core.events;

import com.homeproducts.homeacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AccountOpenedEvent {
    private String id;
    private String accountHandlerId;
    private AccountType accountType;
    private Date creationDate;
    private double openingBalance;
}
