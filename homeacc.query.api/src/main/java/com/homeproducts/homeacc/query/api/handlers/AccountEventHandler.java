package com.homeproducts.homeacc.query.api.handlers;

import com.homeproducts.homeacc.core.events.AccountClosedEvent;
import com.homeproducts.homeacc.core.events.AccountOpenedEvent;
import com.homeproducts.homeacc.core.events.FundsDepositEvent;
import com.homeproducts.homeacc.core.events.FundsWithdrawnEvent;

public interface AccountEventHandler {
    void on(AccountOpenedEvent event);

    void on(FundsDepositEvent event);

    void on(FundsWithdrawnEvent event);

    void on(AccountClosedEvent event);
}
