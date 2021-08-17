package com.homeproducts.homeacc.query.api.handlers.impl;

import com.homeproducts.homeacc.core.events.AccountClosedEvent;
import com.homeproducts.homeacc.core.events.AccountOpenedEvent;
import com.homeproducts.homeacc.core.events.FundsDepositEvent;
import com.homeproducts.homeacc.core.events.FundsWithdrawnEvent;
import com.homeproducts.homeacc.core.models.UserAccount;
import com.homeproducts.homeacc.query.api.handlers.AccountEventHandler;
import com.homeproducts.homeacc.query.api.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("useraccount-group")
@RequiredArgsConstructor
public class AccountEventHandlerImpl implements AccountEventHandler {
    private final AccountRepository repository;

    @Override
    @EventHandler
    public void on(AccountOpenedEvent event) {
        var userAccount = UserAccount.builder()
                .id(event.getId())
                .accountType(event.getAccountType())
                .createDateAccount(event.getCreationDate())
                .accountHolderId(event.getAccountHandlerId())
                .balance(event.getOpeningBalance())
                .build();

        repository.save(userAccount);

    }

    @Override
    @EventHandler
    public void on(FundsDepositEvent event) {
        var userAccount = repository.findById(event.getId());

        if(userAccount.isEmpty()) return;

        userAccount.get().setBalance(event.getBalance());
        repository.save(userAccount.get());


    }

    @Override
    @EventHandler
    public void on(FundsWithdrawnEvent event) {
        var userAccount = repository.findById(event.getId());

        if(userAccount.isEmpty()) return;

        userAccount.get().setBalance(event.getBalance());
        repository.save(userAccount.get());
    }

    @Override
    @EventHandler
    public void on(AccountClosedEvent event) {
        repository.deleteById(event.getId());
    }
}
