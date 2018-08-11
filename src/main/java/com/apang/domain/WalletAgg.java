package com.apang.domain;

import com.apang.api.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate(snapshotTriggerDefinition="customTrigger")
public class WalletAgg {

    @AggregateIdentifier
    private UUID walletId;

    private String name;

    private BigDecimal balance;

    public WalletAgg() {
    }

    @CommandHandler
    public WalletAgg(CreateWalletCommand command) {
        apply(new WalletCreatedEvent(command.getWalletId(), command.getName()));
    }

    @CommandHandler
    public void handle(DepositMoneyCommand command) {
        apply(new MoneyDepositedEvent(command.getWalletId(), command.getAmount()));
    }

    @CommandHandler
    public void handle(DeductMoneyCommand command) {
        if (balance.compareTo(command.getAmount()) >= 0) {
            apply(new MoneyDeductedEvent(command.getWalletId(), command.getAmount()));
        } else {
            throw new IllegalArgumentException("Amount to withdraw is bigger than current balance on account");
        }
    }

    @EventSourcingHandler
    protected void on(WalletCreatedEvent event) {
        this.walletId = event.getWalletId();
        this.balance = BigDecimal.ZERO;
        this.name = event.getName();
    }

    @EventSourcingHandler
    protected void on(MoneyDepositedEvent event) {
        this.balance = balance.add(event.getAmount());
    }

    @EventSourcingHandler
    protected void on(MoneyDeductedEvent event) {
        this.balance = balance.subtract(event.getAmount());
    }
}
