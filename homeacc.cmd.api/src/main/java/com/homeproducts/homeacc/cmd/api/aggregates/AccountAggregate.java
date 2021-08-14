package com.homeproducts.homeacc.cmd.api.aggregates;

import com.homeproducts.homeacc.cmd.api.commands.CloseAccountCommand;
import com.homeproducts.homeacc.cmd.api.commands.DepositAccountCommand;
import com.homeproducts.homeacc.cmd.api.commands.OpenAccountCommand;
import com.homeproducts.homeacc.cmd.api.commands.WithdrawAccountCommand;
import com.homeproducts.homeacc.core.events.AccountClosedEvent;
import com.homeproducts.homeacc.core.events.AccountOpenedEvent;
import com.homeproducts.homeacc.core.events.FundsDepositEvent;
import com.homeproducts.homeacc.core.events.FundsWithdrawnEvent;
import lombok.Builder;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.nio.channels.WritableByteChannel;
import java.util.Date;

@Data
@Builder
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private String accountHolderId;
    private double balance;

    @CommandHandler
    public AccountAggregate(OpenAccountCommand command) {
        var event = AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHandlerId(command.getAccountHolderId())
                .accountType(command.getAccountType())
                .creationDate(new Date())
                .openingBalance(command.getOpenBalance())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountOpenedEvent event) {
        this.id = event.getId();
        this.accountHolderId = event.getAccountHandlerId();
        this.balance = event.getOpeningBalance();
    }

    @CommandHandler
    public void handle(DepositAccountCommand command){
        var amount = command.getAmount();
        var event = FundsDepositEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(this.balance+amount)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsDepositEvent event) {
        this.balance += event.getAmount();
    }

    @CommandHandler
    public void handle(WithdrawAccountCommand command ) {
        var amount = command.getAmount();

        if (this.balance-amount<0) {
            throw new IllegalStateException("Withdraw decline, insufficient funds!");
        }

        var event = FundsWithdrawnEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(this.balance-amount)
                .build();

        AggregateLifecycle.apply(event);

    }

    @EventSourcingHandler
    public void on(FundsWithdrawnEvent event){
        this.balance -= event.getAmount();
    }

    @CommandHandler
    public void handler(CloseAccountCommand command){
        var event = AccountClosedEvent.builder()
                .id(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountClosedEvent event){
        AggregateLifecycle.markDeleted();
    }

}
