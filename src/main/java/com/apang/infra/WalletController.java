package com.apang.infra;

import com.apang.api.CreateWalletCommand;
import com.apang.api.DeductMoneyCommand;
import com.apang.api.DepositMoneyCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
public class WalletController {

    private static final Logger log = LoggerFactory.getLogger(WalletController.class);

    private final CommandGateway commandGateway;

    @Autowired
    public WalletController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create-wallet")
    public CompletableFuture<Object> createWallet(@RequestBody String name) {
        log.info("Request to create wallet for: {}", name);

        assertNotNull(name, "The name of the wallet holder should not be null");

        UUID walletId = UUID.randomUUID();

        CreateWalletCommand createAccountCommand = new CreateWalletCommand(walletId, name);

        return commandGateway.send(createAccountCommand);
    }

    @PostMapping("/deposit-wallet")
    public CompletableFuture<Object> depositMoney(@RequestBody DepositMoneyCommand command) {
        log.info("Request to deposit from wallet {} ", command);

        return commandGateway.send(command);
    }

    @PostMapping("/deduct-wallet")
    public CompletableFuture<Object> withdrawMoney(@RequestBody DeductMoneyCommand command) {
        log.info("Request to deduct from wallet {} ", command);

        return commandGateway.send(command);
    }
}
