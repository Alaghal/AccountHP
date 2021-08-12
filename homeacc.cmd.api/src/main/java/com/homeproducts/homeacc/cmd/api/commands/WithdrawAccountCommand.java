package com.homeproducts.homeacc.cmd.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class WithdrawAccountCommand {
    @TargetAggregateIdentifier
    private String id;
    private double amount;
}
