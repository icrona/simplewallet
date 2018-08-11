package com.apang.api

import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.*

data class CreateWalletCommand(
        @TargetAggregateIdentifier val walletId: UUID,
        val name: String?
)

data class DepositMoneyCommand(
        @TargetAggregateIdentifier val walletId: UUID,
        val amount: BigDecimal
)

data class DeductMoneyCommand(
        @TargetAggregateIdentifier val walletId: UUID,
        val amount: BigDecimal
)