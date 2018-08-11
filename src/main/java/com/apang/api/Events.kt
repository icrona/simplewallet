package com.apang.api

import java.math.BigDecimal
import java.util.*

data class WalletCreatedEvent(
        val walletId: UUID,
        val name: String?
)
data class MoneyDepositedEvent(
        val walletId: UUID,
        val amount: BigDecimal
)

data class MoneyDeductedEvent(
        val walletId: UUID,
        val amount: BigDecimal
)