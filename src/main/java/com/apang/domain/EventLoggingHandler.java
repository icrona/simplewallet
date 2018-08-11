package com.apang.domain;

import com.apang.api.MoneyDeductedEvent;
import com.apang.api.MoneyDepositedEvent;
import com.apang.api.WalletCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("subscribingpoint")
public class EventLoggingHandler {
    private static final Logger LOG = LoggerFactory.getLogger(EventLoggingHandler.class);

    @EventHandler
    public void on(WalletCreatedEvent ev) {
        LOG.info("EventType: {} ID: {} Owner: {}", WalletCreatedEvent.class.getName(), ev.getWalletId(), ev.getName());
    }

    @EventHandler
    public void on(MoneyDepositedEvent ev) {
        LOG.info("EventType: {} ID: {} Amount: {}", MoneyDepositedEvent.class.getName(), ev.getWalletId(), ev.getAmount());
    }

    @EventHandler
    public void on(MoneyDeductedEvent ev) {
        LOG.info("EventType: {} ID: {} Amount: {}", MoneyDeductedEvent.class.getName(), ev.getWalletId(), ev.getAmount());
    }
}
